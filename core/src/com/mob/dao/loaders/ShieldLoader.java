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

import java.io.DataInputStream;
import java.io.IOException;
import com.badlogic.gdx.utils.LongMap;

import com.mob.dao.objects.Shield;
import org.ini4j.Config;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

public class ShieldLoader extends Loader<LongMap<Shield>> {

	@Override
	public LongMap<Shield> load(DataInputStream file) throws IOException {
		LongMap<Shield> shields = new LongMap<Shield>();
		Ini iniFile = new Ini();
		Config c = new Config();
		c.setLowerCaseSection(true);
		iniFile.setConfig(c);
		
		try {
			iniFile.load(file);
			
			int numShields = Integer.parseInt(iniFile.get("init", "NumEscudos"));

			for(int i = 1; i <= numShields; i++) {
				int[] shieldIndex = new int[4];
				
				if(iniFile.get("esc" + String.valueOf(i), "Dir1") == null) { 
					shieldIndex[Heading.NORTH.toInt()] = 0;
					shieldIndex[Heading.EAST.toInt()] = 0; 
					shieldIndex[Heading.SOUTH.toInt()] = 0; 
					shieldIndex[Heading.WEST.toInt()] = 0; 
					shields.put(i, new Shield(shieldIndex));
					continue; 
				}
				
				shieldIndex[Heading.NORTH.toInt()] = Integer.parseInt(iniFile.get("esc" + String.valueOf(i), "Dir1"));
				shieldIndex[Heading.EAST.toInt()] = Integer.parseInt(iniFile.get("esc" + String.valueOf(i), "Dir2"));
				shieldIndex[Heading.SOUTH.toInt()] = Integer.parseInt(iniFile.get("esc" + String.valueOf(i), "Dir3"));
				shieldIndex[Heading.WEST.toInt()] = Integer.parseInt(iniFile.get("esc" + String.valueOf(i), "Dir4"));
				
				shields.put(i, new Shield(shieldIndex));
			}

			return shields;
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

}
