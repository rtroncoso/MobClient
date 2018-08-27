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
import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.LongMap;
import com.mob.dao.objects.Graphic;
import com.mob.dao.objects.Tile;
import com.mob.client.util.Util;

public class GraphicLoader extends Loader<LongMap<Graphic>> {

	@Override
	public LongMap<Graphic> load(DataInputStream file) throws IOException {
        int grh = 0;
        LongMap<Graphic> inits = new LongMap<Graphic>();

        file.skipBytes(4);
        int numGraphics = Util.leInt(file.readInt());
        //inits.ensureCapacity(numGraphics);

        try {
            do {
                int fileNum = 0, sX = 0, sY = 0, numFrames, pixelWidth, pixelHeight, frames[] = new int[0];
                float speed = 0.0f, tileWidth, tileHeight;

                grh = Util.leInt(file.readInt());
                numFrames = Util.leShort(file.readShort());

                if(numFrames > 1) {
                    frames = new int[numFrames];
                    for(int j=0; j < numFrames; j++) {
                        frames[j] = Util.leInt(file.readInt());
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
                    fileNum = Util.leInt(file.readInt());

                    if(fileNum <= 0) throw new IOException("fileNum");

                    sX = Util.leShort(file.readShort());
                    if(sX < 0) throw new IOException("sX (numFrames < 1)");

                    sY = Util.leShort(file.readShort());
                    if(sY < 0) throw new IOException("sY (numFrames < 1)");

                    pixelWidth = Util.leShort(file.readShort());
                    if(pixelWidth <= 0) throw new IOException("pixelWidth (numFrames < 1)");

                    pixelHeight = Util.leShort(file.readShort());
                    if(pixelHeight <= 0) throw new IOException("pixelHeight (numFrames < 1)");

                    tileWidth = (float) pixelWidth / Tile.TILE_PIXEL_WIDTH;
                    tileHeight = (float) pixelHeight / Tile.TILE_PIXEL_HEIGHT;
                }

                inits.put(grh, new Graphic(sX, sY, fileNum, pixelWidth, pixelHeight, tileWidth, tileHeight, frames, speed));
            } while(grh > 0);
        } catch(EOFException ex) {
            return inits;
        }
		
		throw new RuntimeException("Unable to read graphics assets file");
	}

}
