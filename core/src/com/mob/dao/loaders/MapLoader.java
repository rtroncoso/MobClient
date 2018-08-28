/*******************************************************************************
 * Copyright (C) 2014  Rodrigo Troncoso
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.mob.dao.loaders;

import com.badlogic.gdx.utils.Json;
import com.mob.dao.objects.Map;
import com.mob.dao.objects.Tile;
import com.mob.dao.objects.WorldPosition;
import com.mob.client.util.Util;

import java.io.DataInputStream;
import java.io.IOException;

public class MapLoader extends Loader<Map> {

    @Override
    public Map load(DataInputStream file) throws IOException {
        file.skipBytes(GAME_FILE_HEADER_SIZE + (2 * 5)); // Skip complete map header
        Map map = new Map();

        // Read map info (rows first, then columns)
        for(int y = MIN_MAP_SIZE_WIDTH; y <= MAX_MAP_SIZE_WIDTH; y++) {
            for(int x = MIN_MAP_SIZE_HEIGHT; x <= MAX_MAP_SIZE_HEIGHT; x++) {
                int charIndex = 0, objIndex = 0, npcIndex = 0, trigger = 0, graphic[] = new int[4];
                WorldPosition tileExit = new WorldPosition(0, 0, 0);
                boolean blocked = false;
                byte byFlags = 0;

                byFlags = file.readByte();
                blocked = (1 == (byFlags & 1));

                graphic[0] = Util.leShort(file.readShort());

                if((byFlags & 2) == 2) {
                    graphic[1] = Util.leShort(file.readShort());
                } else {
                    graphic[1] = 0;
                }

                if((byFlags & 4) == 4) {
                    graphic[2] = Util.leShort(file.readShort());
                } else {
                    graphic[2] = 0;
                }

                if((byFlags & 8) == 8) {
                    graphic[3] = Util.leShort(file.readShort());
                } else {
                    graphic[3] = 0;
                }

                if((byFlags & 16) == 16) {
                    trigger = Util.leShort(file.readShort());
                }

                map.setTile(x, y, new Tile(graphic, charIndex, objIndex, npcIndex, tileExit, blocked, trigger));
            }
        }

        return map;
    }

}
