package com.mob.server.core;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.utils.ImmutableBag;
import com.esotericsoftware.minlog.Log;
import com.mob.network.notifications.EntityUpdate;
import com.mob.network.notifications.MovementNotification;
import com.mob.server.database.model.User;
import com.mob.server.network.NetworkComunicator;
import com.mob.shared.util.WorldUtils;
import entity.Heading;
import net.mostlyoriginal.api.network.common.DeltaSubscriptionManager;
import physics.AOPhysics;
import position.WorldPos;

import java.util.*;

import static com.artemis.E.E;

public class WorldManager {

    enum Action {
        ADD,
        REMOVE;
    }
    private static Map<Integer, Integer> playerByConnection = new HashMap<>();

    private static Map<Integer, Integer> connectionByPlayer = new HashMap<>();
    private static Map<Integer, Set<Integer>> playersByMap = new HashMap<>();

    public static Entity createEntity(User user, int connectionId) {
        Entity player = getWorld().createEntity();
        E(player)
                .focused()
                .pos2DX(50)
                .pos2DY(50)
                .expExp(10000)
                .worldPosX(50)
                .worldPosY(50)
                .worldPosMap(1)
                .elvElv(1000)
                .levelLevel(45)
                .headingCurrent(Heading.HEADING_NORTH)
                .headIndex(4)
                .bodyIndex(100)
                .weaponIndex(8)
                .shieldIndex(3)
                .helmetIndex(6)
                .healthMin(120)
                .healthMax(120)
                .hungryMin(100)
                .hungryMax(100)
                .manaMax(1000)
                .manaMin(1000)
                .staminaMin(100)
                .staminaMax(100)
                .thirstMax(100)
                .thirstMin(100)
                .criminal()
                .nameName("guidota")
                .clanClan("Clarineta")
                .canWrite()
                .networkId(player.getId())
                .aOPhysics();
        return player;
    }

    public static void registerUser(int connectionId, int id) {
        registerUserConnection(id, connectionId);
        updatePlayerMap(id, E(id).getWorldPos().map, Action.ADD);
    }

    public static void unregisterUser(int connectionId) {
        int playerToDisconnect = getPlayerByConnection(connectionId);

        getSubscriptionManager().unsubscribeFromAll(playerToDisconnect);

        playersByMap.get(E(connectionId).getWorldPos().map).remove(playerToDisconnect);
        getWorld().delete(playerToDisconnect);
        unregisterUserConnection(playerToDisconnect, connectionId);
    }

    public static Set<Integer> getPlayersInMap(int map) {
        return playersByMap.get(map);
    }

    private static World getWorld() {
        return WorldServer.getWorld();
    }

    private static DeltaSubscriptionManager getSubscriptionManager() {
        return WorldServer.getSubscriptionManager();
    }

    public static User getUser(String username) {
        User user = new User();
        return user;
    }

    public static void registerUserConnection(int playerId, int connectionId) {
        playerByConnection.put(connectionId, playerId);
        connectionByPlayer.put(playerId, connectionId);
    }

    public static void unregisterUserConnection(int playerId, int connectionId) {
        playerByConnection.remove(connectionId, playerId);
        connectionByPlayer.put(playerId, connectionId);
    }

    public static boolean connectionHasPlayer(int connectionId) {
        return playerByConnection.containsKey(connectionId);
    }

    public static int getPlayerByConnection(int connectionId) {
        return playerByConnection.get(connectionId);
    }

    public static int getConnectionByPlayer(int playerId) {
        return connectionByPlayer.get(playerId);
    }

    private static void subscribeByProximity(int player, Set<Integer> playersInMap) {
        playersInMap.forEach(player2 -> {
            if (player2 != player && WorldUtils.distance(E(player).getWorldPos(), E(player2).getWorldPos()) < 15) {
                Log.info("Subscribing " + player + " and " + player2);
                getSubscriptionManager().subscribe(player, player2);
                getSubscriptionManager().subscribe(player2, player);
            }
        });
    }

    public static void updatePlayerMap(int player, int map, Action action) {
        Log.info("Updating " + player + " in map " + map);
        Set<Integer> playersInMap = updatePlayersByMap(player, map, action);
        subscribeByProximity(player, playersInMap);
        sendWorldEntities(player);
    }

    private static Set<Integer> updatePlayersByMap(int player, int map, Action action) {
        Set<Integer> playersInMap;
        if (playersByMap.containsKey(map)) {
            playersInMap = playersByMap.get(E(player).getWorldPos().map);
        } else {
            playersInMap = new HashSet<>();
            playersByMap.put(E(player).getWorldPos().map, playersInMap);
        }
        if (action == Action.ADD) {
            playersInMap.add(player);
        } else {
            playersInMap.remove(player);
        }
        return playersInMap;
    }

    public static void updateUserMoved(int playerId, WorldPos nextPos) {
        ImmutableBag<Integer> subscribed = getSubscriptionManager().getSubscribersOf(playerId);
        // notify or unsuscribe
        subscribed.forEach(user -> {
            if (WorldUtils.distance(E(user).getWorldPos(), nextPos) > 15) {
                getSubscriptionManager().unsubscribe(playerId, user);
                getSubscriptionManager().unsubscribe(user, playerId);
            } else {
                if (connectionByPlayer.containsKey(user)) {
                    sendEntityUpdate(getConnectionByPlayer(user), playerId, Arrays.asList(nextPos));
                }
            }
        });
        playersByMap.get(nextPos.map).forEach(playerInMap -> {
            if (playerInMap != playerId && !subscribed.contains(playerInMap)) {
                if (WorldUtils.distance(E(playerInMap).getWorldPos(), nextPos) < 15) {
                    getSubscriptionManager().subscribe(playerId, playerInMap);
                    sendEntityUpdate(getConnectionByPlayer(playerInMap), playerId, Arrays.asList(nextPos));
                }
            }
        });
    }

    private static void sendWorldEntities(int player) {
        getSubscriptionManager().getSubscribersOf(player).forEach(nearPlayer -> {
            Log.debug("Trying to send entity");
            if (connectionByPlayer.containsKey(nearPlayer)) {
                sendNewEntity(player, nearPlayer);
            }
            if (connectionByPlayer.containsKey(player)) {
                sendNewEntity(nearPlayer, player);
            }
        });
    }

    private static void sendNewEntity(int newEntity, int toPlayer) {
        Log.info("Send new entity " + newEntity  + " to " + toPlayer);
        sendEntityUpdate(getConnectionByPlayer(toPlayer), newEntity, WorldUtils.getComponents(getWorld().getEntity(newEntity)));
    }

    private static void sendEntityUpdate(int connectionId, int playerId, List<Component> components) {
        NetworkComunicator.sendTo(connectionId, new EntityUpdate(playerId, components));
    }

    public static void sendEntityMovement(int entityId, AOPhysics.Movement movement) {
        getSubscriptionManager().getSubscribersOf(entityId).forEach(user -> {
            if (connectionByPlayer.containsKey(user)) {
                NetworkComunicator.sendTo(getConnectionByPlayer(user), new MovementNotification(user, movement.toString()));
            }
        });
    }

}
