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
package com.mob.client.loaders;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.mob.client.data.Body;
import com.mob.client.interfaces.Loadable;
import com.mob.client.util.Util;

public class BodyLoader extends Loader implements Loadable<Body> {


	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================


	// ===========================================================
	// Constructors
	// ===========================================================


	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public Vector<Body> load(String initFileName) {
		Vector<Body> bodys = new Vector<Body>();
		this.mFileHandle = Gdx.files.internal(GAME_INIT_PATH + initFileName);
		int numBodys;
		
		DataInputStream file = new DataInputStream(mFileHandle.read());
		
		try {
			file.skipBytes(GAME_FILE_HEADER_SIZE);
			numBodys = Util.leShort(file.readShort());
			bodys.setSize(numBodys + 1);
			
			for(int i = 1; i <= numBodys; i++) {
				int grhArray[] = new int[4], headOffSetX, headOffSetY;
				
				grhArray[Heading.NORTH.toInt()] = Util.leShort(file.readShort());
				grhArray[Heading.EAST.toInt()] = Util.leShort(file.readShort());
				grhArray[Heading.SOUTH.toInt()] = Util.leShort(file.readShort());
				grhArray[Heading.WEST.toInt()] = Util.leShort(file.readShort());
				
				headOffSetX = Util.leShort(file.readShort());
				headOffSetY = Util.leShort(file.readShort());
				
				bodys.setElementAt(new Body(grhArray, headOffSetX, headOffSetY), i);
			}
			Gdx.app.log(this.getClass().getSimpleName(), "Carga de " + initFileName + " con exito");
			return bodys;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
