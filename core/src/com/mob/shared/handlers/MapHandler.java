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
package com.mob.shared.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mob.shared.data.Map;
import com.mob.client.interfaces.Constants;
import com.mob.shared.readers.MapReader;

public class MapHandler implements Constants {

	private static FileHandle fileHandle;
	private static HashMap<Integer, Map> mapData = new HashMap<Integer, Map>();
	private static MapReader reader = new MapReader();

	public static void set(Map map, int mapNumber) {
		if(MapHandler.mapData.containsKey(mapNumber)) return;
		MapHandler.mapData.put(mapNumber, map);
	}
	
	public static Map get(int mapNumber) {
		if(!MapHandler.mapData.containsKey(mapNumber)) load(mapNumber);
		return MapHandler.mapData.get(mapNumber);
	}

	private static boolean load(int mapNumber) {

		Map map = reader.read(String.valueOf(mapNumber));
		set(map, mapNumber);

		Gdx.app.log(MapHandler.class.getSimpleName(), "Map" + String.valueOf(mapNumber) + ".map successfully loaded");
		return true;
	}

}
