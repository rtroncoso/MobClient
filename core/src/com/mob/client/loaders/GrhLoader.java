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
import java.io.EOFException;
import java.io.IOException;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.mob.client.data.GrhData;
import com.mob.client.interfaces.Loadable;
import com.mob.client.util.Util;

public class GrhLoader extends Loader implements Loadable<GrhData> {

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
	public Vector<GrhData> load(String initFileName) {
		Vector<GrhData> inits = new Vector<GrhData>();
		this.mFileHandle = Gdx.files.internal(GAME_INIT_PATH + initFileName);
		
		try {
			DataInputStream file = new DataInputStream(mFileHandle.read());
			file.skipBytes(4);
			int numGrhs = Util.leShort(file.readShort());
			file.skipBytes(2);
			
			inits.setSize(numGrhs + 1);
			inits.setElementAt(new GrhData(0, 0, 0, 0, 0, 0, 0, new int[0], 0), 0);
			
			int grh = Util.leShort(file.readShort());
			file.skipBytes(2); // no es negro si nadie lo ve
			
			while(grh > 0) {
				int fileNum = 0, sX = 0, sY = 0, numFrames = 0, pixelWidth = 0, pixelHeight = 0, frames[] = new int[0];
				float speed = 0.0f, tileWidth = 0.0f, tileHeight = 0.0f;
				numFrames = Util.leShort(file.readShort());
				
				if(numFrames > 1) {
					frames = new int[numFrames];
					for(int j=0; j < numFrames; j++) {
						frames[j] = Util.leShort(file.readShort());
						file.skipBytes(2);
						if(frames[j] <= 0) throw new IOException("frames[]: " + frames[j]);
					}

					// Hardcodeamos speed (Java no lee single floating points de VB)
					file.skipBytes(4);
					speed = (numFrames * 1000) / 60;
					if(speed <= 0) throw new IOException("speed (numFrames > 1)");

					pixelWidth = inits.get(frames[0]).getPixelWidth();
					if(pixelWidth <= 0) throw new IOException("pixelWidth (numFrames > 1)");
					
					pixelHeight = inits.get(frames[0]).getPixelHeight();
					if(pixelHeight <= 0) throw new IOException("pixelHeight (numFrames > 1)");
					
					tileWidth = inits.get(frames[0]).getTileWidth();
					if(tileWidth <= 0) throw new IOException("tileWidth (numFrames > 1)");
					tileHeight = inits.get(frames[0]).getTileHeight();
					if(tileHeight <= 0) throw new IOException("tileHeight (numFrames > 1)");
				} else {
					// Read normal GRH
					fileNum = Util.leShort(file.readShort());
					file.skipBytes(2);
					
					if(fileNum <= 0) throw new IOException("fileNum");
					
					sX = Util.leShort(file.readShort());
					if(sX < 0) throw new IOException("sX (numFrames < 1)");
					
					sY = Util.leShort(file.readShort());
					if(sY < 0) throw new IOException("sY (numFrames < 1)");
					
					pixelWidth = Util.leShort(file.readShort());
					if(pixelWidth <= 0) throw new IOException("pixelWidth (numFrames < 1)");
					
					pixelHeight = Util.leShort(file.readShort());
					if(pixelHeight <= 0) throw new IOException("pixelHeight (numFrames < 1)");

					tileWidth = (float) pixelWidth / TILE_PIXEL_WIDTH;
					tileHeight = (float) pixelHeight / TILE_PIXEL_HEIGHT;
				}
				inits.setElementAt(new GrhData(sX, sY, fileNum, pixelWidth, pixelHeight, tileWidth, tileHeight, frames, speed), grh);
				grh = Util.leShort(file.readShort());
				file.skipBytes(2);
			}
			
		} catch (EOFException e) {
			Gdx.app.log(this.getClass().getSimpleName(), "Carga de " + initFileName + " con exito");
			return inits;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	// ===========================================================
	// Getter & Setter
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
