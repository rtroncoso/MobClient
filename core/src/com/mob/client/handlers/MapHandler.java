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
package com.mob.client.handlers;

import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mob.client.Game;
import com.mob.client.data.Map;
import com.mob.client.interfaces.Constants;
import com.mob.client.loaders.MapLoader;

public class MapHandler implements Constants {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private static FileHandle fileHandle;
	private static HashMap<Integer, Map> mapData = new HashMap<Integer, Map>();
	private static MapLoader loader = new MapLoader();

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
	public static void set(Map map, int mapNumber) {
		if(MapHandler.mapData.containsKey(mapNumber)) return;
		MapHandler.mapData.put(mapNumber, map);
	}
	
	public static Map get(int mapNumber) {
		if(!MapHandler.mapData.containsKey(mapNumber)) load(mapNumber);
		return MapHandler.mapData.get(mapNumber);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	private static boolean load(int mapNumber) {

		try {
			Map map = loader.load(String.valueOf(mapNumber) + ".map");
            set(map, mapNumber);
		} catch(IOException e) {
			Gdx.app.log(MapHandler.class.getSimpleName(), e.getMessage());
			e.printStackTrace();
			return false;
		}

		Gdx.app.log(MapHandler.class.getSimpleName(), "Map" + String.valueOf(mapNumber) + ".map successfully loaded");
		return true;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
