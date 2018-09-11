package com.mob.shared.util;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.utils.Bag;
import com.mob.client.handlers.MapHandler;
import com.mob.dao.objects.Tile;
import com.mob.server.core.WorldManager;
import physics.AOPhysics;
import position.WorldPos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.artemis.E.E;

public class WorldUtils {

    public static int distance(WorldPos pos1, WorldPos pos2) {
        return Math.abs((pos1.x - pos2.x) + (pos1.y - pos2.y));
    }

    public static boolean isValidPos(WorldPos worldPos) {
        Tile tile = MapHandler.get(worldPos.map).getTile(worldPos.x, worldPos.y);
        return !(tile.isBlocked() || tile.getCharIndex() != -1);
    }

    public static boolean isValidWorldPos(WorldPos worldPos) {
        Set<Integer> playersInMap = WorldManager.getPlayersInMap(worldPos.map);
        return !playersInMap.stream().anyMatch(player -> (E(player).getWorldPos().x == worldPos.x) && (E(player).getWorldPos().y == worldPos.y));
    }


    public static WorldPos getNextPos(WorldPos pos, AOPhysics.Movement movement) {
        return new WorldPos(
                (movement == AOPhysics.Movement.RIGHT ? 1 : movement == AOPhysics.Movement.LEFT ? -1 : 0) + pos.x,
                (movement == AOPhysics.Movement.UP ? -1 : movement == AOPhysics.Movement.DOWN ? 1 : 0) + pos.y,
                pos.map);
    }

    public static List<Component> getComponents(Entity player) {
        Bag<Component> components = player.getComponents(new Bag<>());
        List<Component> componentsToSend = new ArrayList<>();
        components.forEach(component -> {
            if (component != null) {
                componentsToSend.add(component);
            }
        });
        return componentsToSend;
    }
}
