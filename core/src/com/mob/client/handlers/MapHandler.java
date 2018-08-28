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

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.mob.dao.objects.Map;
import com.mob.client.interfaces.Constants;
import com.mob.dao.readers.AODescriptorsReader;
import com.mob.dao.readers.DescriptorsReader;

public class MapHandler implements Constants {

	private static HashMap<Long, Map> mapData = new HashMap<Long, Map>();
	private static DescriptorsReader reader = new AODescriptorsReader();

	public static Map get(long mapNumber) {
		if(!MapHandler.mapData.containsKey(mapNumber)) load(mapNumber);
		return MapHandler.mapData.get(mapNumber);
	}

	private static boolean load(long mapNumber) {

		Map map = getJson().fromJson(Map.class, Gdx.files.internal("data/maps/" + "Mapa" + mapNumber + ".json"));
		mapData.put(mapNumber, map);

		Gdx.app.log(MapHandler.class.getSimpleName(),
				"[MapHandler] Map " + String.valueOf(mapNumber)
						+ ".map successfully loaded");
		return true;
	}

//	public static void mapsToJson() {
//		for(int i = 1; i <= 290; i++) {
//			Map map = reader.loadMap(String.valueOf(i));
//			Json json = getJson();
//			try (PrintWriter out = new PrintWriter("data/jsonMaps/" + "Mapa" + i + ".json")) {
//				out.print(json.prettyPrint(map));
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	private static Json getJson() {
		Json json = new Json();
		json.addClassTag("map", Map.class);
		return json;
	}
}
