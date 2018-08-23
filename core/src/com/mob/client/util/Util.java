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
/**
 * General math methods
 * TODO : 
 * 	- Split into a Math class and give everything it's right place
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.util;


import com.mob.dao.objects.Tile;
import position.Pos2D;
import position.WorldPos;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Util {

	public static short leShort(short n) {
		return (short) (((n & 0xff) << 8) | (((n & 0xff00) >> 8) & 0xff));
	}

	public static int leInt(int n) {
		ByteBuffer buf = ByteBuffer.allocate(4);
		buf.order(ByteOrder.BIG_ENDIAN);
		buf.putInt(n);
		buf.order(ByteOrder.LITTLE_ENDIAN);

		return buf.getInt(0);
	}
	
	public static int leFloat(float n) {

        ByteBuffer buf = ByteBuffer.allocate(4);
        buf.order(ByteOrder.BIG_ENDIAN);
        buf.putFloat(n);
        buf.order(ByteOrder.LITTLE_ENDIAN);

        return buf.getInt(0);
	}

	public static Pos2D toScreen(WorldPos worldPos) {
		return new Pos2D(worldPos.x * Tile.TILE_PIXEL_WIDTH,
				worldPos.y * Tile.TILE_PIXEL_HEIGHT);
	}

	public static Pos2D toScreen(Pos2D pos) {
		return new Pos2D(pos.x * Tile.TILE_PIXEL_WIDTH,
				pos.y * Tile.TILE_PIXEL_HEIGHT);
	}

	public static Pos2D toGUI(Pos2D pos) {
		return new Pos2D(pos.x * Tile.TILE_PIXEL_WIDTH,
				pos.y * Tile.TILE_PIXEL_HEIGHT);
	}

	public static WorldPos toWorld(Pos2D pos) {
		return new WorldPos((int) (pos.x / Tile.TILE_PIXEL_WIDTH),
				(int) (pos.y / Tile.TILE_PIXEL_HEIGHT));
	}

}
