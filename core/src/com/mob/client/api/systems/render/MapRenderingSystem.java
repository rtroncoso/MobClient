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
package com.mob.client.api.systems.render;

import com.artemis.annotations.Wire;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mob.client.data.Map;
import com.mob.client.api.systems.camera.CameraSystem;
import com.mob.client.api.systems.map.TiledMapSystem;

/**
 * MapRenderSystem Class
 *
 * @author rt
 * @package com.mob.client.api.systems.render
 */
@Wire
public class MapRenderingSystem extends VoidEntitySystem {

    // ===========================================================
    // Constants
    // ===========================================================
    public static final int MAP_START_LAYER = 1;
    public static final int MAP_END_LAYER = 4;

    // ===========================================================
    // Fields
    // ===========================================================
    private TiledMapSystem mMapSystem;
    private CameraSystem mCameraSystem;
    private SpriteBatch mBatch;

    // ===========================================================
    // Constructors
    // ===========================================================
    public MapRenderingSystem(SpriteBatch pSpriteBatch) {
        this.mBatch = pSpriteBatch;
    }


    // ===========================================================
    // Methods
    // ===========================================================
    private void renderWorld() {

        // Variable Declarations
        int screenMinX, screenMaxX, screenMinY, screenMaxY, minAreaX, minAreaY, maxAreaX, maxAreaY;
        Map map = this.mMapSystem.map;

        // Calculate visible part of the map
        int cameraPosX = (int) (this.mCameraSystem.camera.position.x / Map.TILE_PIXEL_WIDTH);
        int cameraPosY = (int) (this.mCameraSystem.camera.position.y / Map.TILE_PIXEL_HEIGHT);
        int halfWindowTileWidth = (int) ((this.mCameraSystem.camera.viewportWidth / Map.TILE_PIXEL_WIDTH) / 2f);
        int halfWindowTileHeight = (int) ((this.mCameraSystem.camera.viewportHeight / Map.TILE_PIXEL_HEIGHT) / 2f);

        screenMinX = cameraPosX - halfWindowTileWidth - 1;
        screenMaxX = cameraPosX + halfWindowTileWidth + 1;
        screenMinY = cameraPosY - halfWindowTileHeight - 1;
        screenMaxY = cameraPosY + halfWindowTileHeight + 1;

        minAreaX = screenMinX - Map.TILE_BUFFER_SIZE;
        maxAreaX = screenMaxX + Map.TILE_BUFFER_SIZE;
        minAreaY = screenMinY - Map.TILE_BUFFER_SIZE;
        maxAreaY = screenMaxY + Map.TILE_BUFFER_SIZE;

        // Make sure it is between map bounds
        if(minAreaX < Map.MIN_MAP_SIZE_WIDTH) minAreaX = Map.MIN_MAP_SIZE_WIDTH;
        if(maxAreaX > Map.MAX_MAP_SIZE_WIDTH) maxAreaX = Map.MAX_MAP_SIZE_WIDTH;
        if(minAreaY < Map.MIN_MAP_SIZE_HEIGHT) minAreaY = Map.MIN_MAP_SIZE_HEIGHT;
        if(maxAreaY > Map.MAX_MAP_SIZE_HEIGHT) maxAreaY = Map.MAX_MAP_SIZE_HEIGHT;

        if(screenMinX < Map.MIN_MAP_SIZE_WIDTH) screenMinX = Map.MIN_MAP_SIZE_WIDTH;
        if(screenMaxX > Map.MAX_MAP_SIZE_WIDTH) screenMaxX = Map.MAX_MAP_SIZE_WIDTH;
        if(screenMinY < Map.MIN_MAP_SIZE_HEIGHT) screenMinY = Map.MIN_MAP_SIZE_HEIGHT;
        if(screenMaxY > Map.MAX_MAP_SIZE_HEIGHT) screenMaxY = Map.MAX_MAP_SIZE_HEIGHT;

        // Start rendering our map's first layer (buffered)
        this.mBatch.draw(map.getBufferedLayer(), screenMinX, screenMinY);

        // Iteramos todas las layers del map y las renderizamos
        for(int layer = MAP_START_LAYER; layer < MAP_END_LAYER; layer++) {
            map.renderLayer(this.mBatch, layer, screenMinX, screenMaxX, screenMinY, screenMaxY);
        }
    }


    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    @Override
    protected void processSystem() {

        // Obtenemos una entity de la queue y inicializamos el batch
        this.mCameraSystem.camera.update();
        this.mBatch.setProjectionMatrix(this.mCameraSystem.camera.combined);
        this.mBatch.begin();

        // Render our world
        this.renderWorld();

        // Finalizamos el batch
        this.mBatch.end();
    }


    // ===========================================================
    // Getter & Setter
    // ===========================================================


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}
