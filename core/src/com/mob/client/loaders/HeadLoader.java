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
import com.mob.client.data.HeadData;
import com.mob.client.interfaces.Loadable;
import com.mob.client.util.Util;

public class HeadLoader extends Loader implements Loadable<HeadData> {

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
	public Vector<HeadData> load(String initFileName) {
		Vector<HeadData> heads = new Vector<HeadData>();
		this.mFileHandle = Gdx.files.internal(GAME_INIT_PATH + initFileName);
		int numHeads = 0;
		
		DataInputStream file = new DataInputStream(mFileHandle.read());
		
		try {
			file.skipBytes(GAME_FILE_HEADER_SIZE);
			numHeads = Util.leShort(file.readShort());
			heads.setSize(numHeads + 1);
			
			for(int i = 1; i <= numHeads; i++) {
				int headIndex[] = new int[4];
				
				headIndex[Heading.NORTH.toInt()] = Util.leShort(file.readShort());
				headIndex[Heading.EAST.toInt()] = Util.leShort(file.readShort());
				headIndex[Heading.SOUTH.toInt()] = Util.leShort(file.readShort());
				headIndex[Heading.WEST.toInt()] = Util.leShort(file.readShort());

				heads.setElementAt(new HeadData(headIndex), i);
			}
			Gdx.app.log(this.getClass().getSimpleName(), "Carga de " + initFileName + " con exito");
			return heads;
			
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
