package com.mob.server.manager;

import com.badlogic.gdx.utils.Json;
import com.esotericsoftware.minlog.Log;
import com.mob.client.handlers.MapHandler;
import com.mob.client.utils.MapUtils;
import com.mob.dao.objects.Tile;
import com.mob.shared.util.WorldUtils;
import position.WorldPos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.artemis.E.E;

public class MapManager {

    private static Map<Integer, Set<Integer>> nearEntities = new ConcurrentHashMap<>();
    private static Map<Integer, Set<Integer>> playersByMap = new ConcurrentHashMap<>();
    private static HashMap<Integer, com.mob.dao.objects.Map> mapData = new HashMap<>();

    public static Set<Integer> getNearEntities(int entityId) {
        return nearEntities.getOrDefault(entityId, Collections.emptySet());
    }

    public static Set<Integer> getPlayersInMap(int map) {
        return playersByMap.get(map);
    }

    public static void movePlayer(int player, Optional<WorldPos> previusPos) {
        WorldPos actualPos = E(player).getWorldPos();
        previusPos.ifPresent(it -> {
            if (it.equals(actualPos)) {
                return;
            }
            if (it.map != actualPos.map) {
                getPlayersInMap(it.map).remove(player);
            }
            nearEntities.get(player).forEach(nearEntity -> {
                removeNearEntity(player, nearEntity);
            });
        });
        addPlayer(player);
    }

    public static void removePlayer(int playerToDisconnect) {
        int map = E(playerToDisconnect).getWorldPos().map;
        // remove from near entities
        nearEntities.computeIfPresent(playerToDisconnect, (player, removeFrom) -> {
            removeFrom.forEach(nearEntity -> {
                unlinkEntities(nearEntity, playerToDisconnect);
            });
            return null;
        });
        playersByMap.get(map).remove(playerToDisconnect);
    }

    public static void addPlayer(int player1) {
        int map = E(player1).getWorldPos().map;
        Set<Integer> players = playersByMap.computeIfAbsent(map, (it) -> new HashSet<>());
        players.add(player1);
        players.stream()
                .filter(player -> player != player1)
                .forEach(player2 -> {
                    addNearEntities(player1, player2);
                });
    }

    private static void addNearEntities(int player1, int player2) {
        int distance = WorldUtils.distance(player2, player1);
        if (distance >= 0 && distance <= 15) {
            linkEntities(player1, player2);
            linkEntities(player2, player1);
        }
    }

    private static void removeNearEntity(int player1, int player2) {
        int distance = WorldUtils.distance(player2, player1);
        if (distance < 0 || distance > 15) {
            unlinkEntities(player1, player2);
            unlinkEntities(player2, player1);
        }
    }

    private static void unlinkEntities(int player1, int player2) {
        nearEntities.computeIfAbsent(player1, id -> new HashSet<>()).remove(player2);
        // always notify that this entity is not longer on radar
        WorldManager.sendEntityRemove(player1, player2);
    }

    private static void linkEntities(int player1, int player2) {
        Set<Integer> near = nearEntities.computeIfAbsent(player1, (i) -> new HashSet<>());
        if (!near.contains(player2)) {
            Log.debug("linking entities " + player1 + " and " + player2 + "?");
            near.add(player2);
            WorldManager.sendEntityUpdate(player1, player2, WorldUtils.getComponents(player2));
            Log.debug("OK");
        }
    }

    public static void initialize() {
        Log.info("Loading maps...");
        for (int i = 1; i <= 290; i++) {
            try {
                FileInputStream mapStream = new FileInputStream("data/maps/" + "Mapa" + i + ".json");;
                com.mob.dao.objects.Map map = getJson().fromJson(com.mob.dao.objects.Map.class, mapStream);
                mapData.put(i, map);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Log.info("Finish loding maps");
    }

    private static Json getJson() {
        Json json = new Json();
        json.addClassTag("map", com.mob.dao.objects.Map.class);
        return json;
    }

    public static Optional<Tile> getTile(WorldPos expectedPos) {
        com.mob.dao.objects.Map map = MapHandler.get(expectedPos.map);
        return Optional.ofNullable(map.getTile(expectedPos.x, expectedPos.y));
    }

    public static boolean isValidPos(WorldPos worldPos) {
        com.mob.dao.objects.Map map = mapData.get(worldPos.map);
        Set<Integer> playersInMap = MapManager.getPlayersInMap(worldPos.map);
        if (playersInMap.stream().anyMatch(player -> (E(player).getWorldPos().x == worldPos.x) && (E(player).getWorldPos().y == worldPos.y))) {
            return false;
        };
        return MapUtils.isValidPos(map, worldPos);
    }
}
