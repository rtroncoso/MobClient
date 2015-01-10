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
package com.mob.client.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.mob.client.data.MapBlockData;

public class ChunkComponent extends Component {

    // ===========================================================
    // Constants
    // ===========================================================
    public static final int CHUNK_TILE_SIZE = 20;
    public static final int CHUNK_LAYERS = 4;


    // ===========================================================
    // Fields
    // ===========================================================
    public MapBlockData tiles[][] = new MapBlockData[CHUNK_TILE_SIZE + 1][CHUNK_TILE_SIZE + 1];
    public Rectangle bounds = new Rectangle();


    // ===========================================================
    // Constructors
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================
    /**
     * Wrapper para obtener un tile especifico de nuestra lista de tiles
     *
     * @param x
     * @param y
     * @return
     */
    public MapBlockData getTile(int x, int y) {
        return this.tiles[x][y];
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
