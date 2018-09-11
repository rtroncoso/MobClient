package com.mob.client.utils;

import com.artemis.E;
import com.mob.client.handlers.MapHandler;
import com.mob.client.screens.GameScreen;
import com.mob.dao.objects.Map;
import com.mob.dao.objects.Tile;
import com.mob.dao.objects.WorldPosition;
import position.WorldPos;

public class MapUtils {


    public static boolean changeMap(E player, WorldPos pos) {
        Map map = MapHandler.get(pos.map);
        Tile tile = map.getTile(pos.x, pos.y);
        WorldPosition newPos = tile.getTileExit();
        WorldPos playerPos = player.getWorldPos();
        if (newPos.getMap() != 0 && newPos.getMap() != playerPos.map) {
            playerPos.map = newPos.getMap();
            playerPos.x = newPos.getX();
            playerPos.y = newPos.getY();
            updateTile(GameScreen.getPlayer(), playerPos);
            return true;
        }
        return false;
    }

    public static void updateTile(int entity, WorldPos pos) {
        Map map = MapHandler.get(pos.map);
        Tile tile = map.getTile(pos.x, pos.y);
        tile.setCharIndex(entity);
    }

    public static boolean isValid(WorldPos expectedPos) {
        Map map = MapHandler.get(expectedPos.map);
        Tile tile = map.getTile(expectedPos.x, expectedPos.y);
        return tile != null && !tile.isBlocked() && tile.getCharIndex() == Tile.EMPTY_INDEX;
    }
}
