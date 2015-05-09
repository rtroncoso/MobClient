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
package com.mob.shared.loaders;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

import com.mob.shared.data.Shield;
import org.ini4j.Config;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

public class ShieldLoader extends Loader<Vector<Shield>> {

	@Override
	public Vector<Shield> load(DataInputStream file) {
		Vector<Shield> shields = new Vector<Shield>();
		Ini iniFile = new Ini();
		Config c = new Config();
		c.setLowerCaseSection(true);
		iniFile.setConfig(c);
		
		try {
			iniFile.load(file);
			
			int numShields = Integer.parseInt(iniFile.get("init", "NumEscudos"));
			shields.setSize(numShields + 1);
			
			for(int i = 1; i <= numShields; i++) {
				int[] shieldIndex = new int[4];
				
				if(iniFile.get("esc" + String.valueOf(i), "Dir1") == null) { 
					shieldIndex[Heading.NORTH.toInt()] = 0;
					shieldIndex[Heading.EAST.toInt()] = 0; 
					shieldIndex[Heading.SOUTH.toInt()] = 0; 
					shieldIndex[Heading.WEST.toInt()] = 0; 
					shields.setElementAt(new Shield(shieldIndex), i);
					continue; 
				}
				
				shieldIndex[Heading.NORTH.toInt()] = Integer.parseInt(iniFile.get("esc" + String.valueOf(i), "Dir1"));
				shieldIndex[Heading.EAST.toInt()] = Integer.parseInt(iniFile.get("esc" + String.valueOf(i), "Dir2"));
				shieldIndex[Heading.SOUTH.toInt()] = Integer.parseInt(iniFile.get("esc" + String.valueOf(i), "Dir3"));
				shieldIndex[Heading.WEST.toInt()] = Integer.parseInt(iniFile.get("esc" + String.valueOf(i), "Dir4"));
				
				shields.setElementAt(new Shield(shieldIndex), i);
			}

			return shields;
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
