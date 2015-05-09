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
package com.mob.client.artemis.systems.render;

import com.artemis.annotations.Wire;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mob.shared.data.Map;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.artemis.systems.map.TiledMapSystem;

/**
 * MapRenderSystem Class
 *
 * @author rt
 * @package com.mob.client.api.systems.render
 */
@Wire
public class MapRenderingSystem extends VoidEntitySystem {

    private TiledMapSystem mapSystem;
    private CameraSystem cameraSystem;
    public SpriteBatch batch;

    public MapRenderingSystem(SpriteBatch spriteBatch) {
        this.batch = spriteBatch;
    }

    private void renderWorld() {

        // Variable Declarations
        int screenMinX, screenMaxX, screenMinY, screenMaxY, minAreaX, minAreaY, maxAreaX, maxAreaY;
        Map map = this.mapSystem.map;

        // Calculate visible part of the map
        int cameraPosX = (int) (this.cameraSystem.camera.position.x / Map.TILE_PIXEL_WIDTH);
        int cameraPosY = (int) (this.cameraSystem.camera.position.y / Map.TILE_PIXEL_HEIGHT);
        int halfWindowTileWidth = (int) ((this.cameraSystem.camera.viewportWidth / Map.TILE_PIXEL_WIDTH) / 2f);
        int halfWindowTileHeight = (int) ((this.cameraSystem.camera.viewportHeight / Map.TILE_PIXEL_HEIGHT) / 2f);

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

        // LAYER 1 - ANIMATED
        map.renderLayer(this.batch, world.getDelta(), 0, screenMinX, screenMaxX, screenMinY, screenMaxY);

        // LAYER 2 - STATIC - FRAMEBUFFERED
        this.batch.draw(map.getBufferedLayer(), 0, 0);

        // LAYER 3 - ANIMATED (POSSIBLY?)
        map.renderLayer(this.batch, world.getDelta(), 2, minAreaX, maxAreaX, minAreaY, maxAreaY);

        // LAYER 4 - ANIMATED (POSSIBLY?)
        map.renderLayer(this.batch, world.getDelta(), 3, minAreaX, maxAreaX, minAreaY, maxAreaY);
    }

    @Override
    protected void processSystem() {

        // Obtenemos una entity de la queue y inicializamos el batch
        this.cameraSystem.camera.update();
        this.batch.setProjectionMatrix(this.cameraSystem.camera.combined);
        this.batch.begin();

        // Render our world
        this.renderWorld();

        // Finalizamos el batch
        this.batch.end();
    }


}
