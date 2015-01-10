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
 * Loads map data from disk and fills into map vector
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.handlers;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mob.client.Game;
import com.mob.client.data.MapBlockData;
import com.mob.client.data.MapData;
import com.mob.client.data.WorldPositionData;
import com.mob.client.interfaces.ConstantsInterface;
import com.mob.client.util.Util;

public class MapHandler implements ConstantsInterface {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private static FileHandle mFileHandle;
	private static HashMap<Integer, MapData> mMapData = new HashMap<Integer, MapData>();

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	/**
	 * @param game
	 */
	public MapHandler(Game game) {
		// TODO Auto-generated constructor stub
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public static void set(MapData pMapData, int pMapNumber) {
		if(MapHandler.mMapData.containsKey(pMapNumber)) return;
		MapHandler.mMapData.put(pMapNumber, pMapData);
	}
	
	public static MapData get(int pMapNumber) {
		if(!MapHandler.mMapData.containsKey(pMapNumber)) loadMap(pMapNumber);
		return MapHandler.mMapData.get(pMapNumber);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	private static boolean loadMap(int pMapNumber) {
		
		MapHandler.mFileHandle = new FileHandle(GAME_MAPS_PATH + "Mapa" + String.valueOf(pMapNumber) + ".map");
		
		DataInputStream file = new DataInputStream(MapHandler.mFileHandle.read());
		try {
			file.skipBytes(GAME_FILE_HEADER_SIZE + (2 * 5)); // Skip complete map header
			MapData mapData = new MapData();

			// Read map info (rows first, then columns)
			for(int y = MIN_MAP_SIZE_WIDTH; y <= MAX_MAP_SIZE_WIDTH; y++) {
				for(int x = MIN_MAP_SIZE_HEIGHT; x <= MAX_MAP_SIZE_HEIGHT; x++) {
					int charIndex = 0, objIndex = 0, npcIndex = 0, trigger = 0, graphic[] = new int[4];
					WorldPositionData tileExit = new WorldPositionData(0, 0, 0);
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
					
					mapData.setTile(x, y, new MapBlockData(graphic, charIndex, objIndex, npcIndex, tileExit, blocked, trigger));
				}
			}
			
			MapHandler.set(mapData, pMapNumber);
			Gdx.app.log(MapHandler.class.getSimpleName(), "Mapa" + String.valueOf(pMapNumber) + ".map cargado con exito.");
			return true;
		} catch(IOException e) {
			Gdx.app.log(MapHandler.class.getSimpleName(), e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
