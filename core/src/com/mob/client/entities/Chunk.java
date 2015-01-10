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
package com.mob.client.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector3;
import com.mob.client.components.ChunkComponent;
import com.mob.client.components.StateComponent;
import com.mob.client.components.TransformComponent;
import com.mob.client.data.MapBlockData;

public class Chunk extends Entity {

    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    private ChunkComponent mChunkCompononent = new ChunkComponent();
    private TransformComponent mTransformComponent = new TransformComponent();
    private StateComponent mStateComponent = new StateComponent();


    // ===========================================================
    // Constructors
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================
    /**
     * Settea un tile especifico adentro de un chunk
     *
     * @param x
     * @param y
     * @param tile
     */
    public void setTile(int x, int y, MapBlockData tile) {
        this.mChunkCompononent.tiles[x][y] = tile;
    }

    /**
     * Settea la posicion inicial de un chunk
     *
     * @param pos
     */
    public void setPosition(Vector3 pos) {
        this.mTransformComponent.pos = pos;
    }

    /**
     * Settea la posicion inicial de un chunk
     *
     * @param x
     * @param y
     */
    public void setPosition(float x, float y) {
        this.setPosition(new Vector3(x, y, 0.0f));
    }


    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Getter & Setter
    // ===========================================================
    /**
     * @return
     */
    public ChunkComponent getChunkCompononent() {
        return mChunkCompononent;
    }

    /**
     * @param mChunkCompononent
     */
    public void setChunkCompononent(ChunkComponent mChunkCompononent) {
        this.mChunkCompononent = mChunkCompononent;
    }

    /**
     * @return
     */
    public TransformComponent getTransformComponent() {
        return mTransformComponent;
    }

    /**
     * @param mTransformComponent
     */
    public void setTransformComponent(TransformComponent mTransformComponent) {
        this.mTransformComponent = mTransformComponent;
    }

    /**
     * @return
     */
    public StateComponent getStateComponent() {
        return mStateComponent;
    }

    /**
     * @param mStateComponent
     */
    public void setStateComponent(StateComponent mStateComponent) {
        this.mStateComponent = mStateComponent;
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}
