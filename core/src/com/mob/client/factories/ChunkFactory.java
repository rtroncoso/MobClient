/*******************************************************************************
 * Copyright (C) 2015  Rodrigo Troncoso
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
package com.mob.client.factories;

import com.badlogic.gdx.utils.Array;
import com.mob.client.components.ChunkComponent;
import com.mob.client.data.MapData;
import com.mob.client.entities.Chunk;

public class ChunkFactory {

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
    public static Array<Chunk> create(MapData map) {

        // Creamos el objeto que va a contener los chunks
        Array<Chunk> chunkArray = new Array<Chunk>();
        int chunksAmmount = 100 / ChunkComponent.CHUNK_TILE_SIZE;

        // Creamos todos los chunks posibles acorde al mapa
        for(int chunkY = 0; chunkY < chunksAmmount; chunkY++) {
            for(int chunkX = 0; chunkX < chunksAmmount; chunkX++) {

                // Creamos el objeto a agregar al array
                Chunk currentChunk = new Chunk();

                // Setteamos la posicion de este chunk
                currentChunk.setPosition(
                    (float) (chunkX * ChunkComponent.CHUNK_TILE_SIZE),
                    (float) (chunkY * ChunkComponent.CHUNK_TILE_SIZE)
                );

                // Iteramos todos los tiles del mapa hasta llenar todos los chunks
                for(int y = 1; y <= ChunkComponent.CHUNK_TILE_SIZE; y++) {
                    for(int x = 1; x <= ChunkComponent.CHUNK_TILE_SIZE; x++) {
                        currentChunk.setTile(x, y, map.getTile(x, y));
                        currentChunk.add(currentChunk.getChunkCompononent());
                        currentChunk.add(currentChunk.getTransformComponent());
                    }
                }

                chunkArray.add(currentChunk);
            }
        }

        // Devolvemos la lista de chunks creada
        return chunkArray;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Getter & Setter
    // ===========================================================


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
