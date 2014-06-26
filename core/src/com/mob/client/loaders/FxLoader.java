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
import com.mob.client.data.FxData;
import com.mob.client.interfaces.Loadable;
import com.mob.client.util.Util;

public class FxLoader extends Loader implements Loadable<FxData> {


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
	public Vector<FxData> load(String initFileName) {
		Vector<FxData> fxs = new Vector<FxData>();
		this.mFileHandle = Gdx.files.internal(GAME_INIT_PATH + initFileName);
		int numFxs = 0;
		
		DataInputStream file = new DataInputStream(mFileHandle.read());
		
		try {
			file.skipBytes(GAME_FILE_HEADER_SIZE);
			numFxs = Util.leShort(file.readShort());
			fxs.setSize(numFxs + 1);
			
			for(int i = 1; i <= numFxs; i++) {
				int offsetX = 0, offsetY = 0, fxIndex = 0;
				
				fxIndex = Util.leShort(file.readShort());
				offsetX = Util.leShort(file.readShort());
				offsetY = Util.leShort(file.readShort());
				
				fxs.setElementAt(new FxData(fxIndex, offsetX, offsetY), i);
			}
			Gdx.app.log(this.getClass().getSimpleName(), "Carga de " + initFileName + " con exito");
			return fxs;
			
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
