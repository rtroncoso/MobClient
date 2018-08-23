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

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mob.dao.objects.Map;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.artemis.systems.map.TiledMapSystem;

/**
 * MapRenderSystem Class
 *
 * @author rt
 * @package com.mob.client.api.systems.render
 */
@Wire
public class MapLowerLayerRenderingSystem extends BaseSystem {

    private TiledMapSystem mapSystem;
    private CameraSystem cameraSystem;
    public SpriteBatch batch;

    public MapLowerLayerRenderingSystem(SpriteBatch spriteBatch) {
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

        screenMinX = cameraPosX - halfWindowTileWidth - 2;
        screenMaxX = cameraPosX + halfWindowTileWidth + 2;
        screenMinY = cameraPosY - halfWindowTileHeight - 2;
        screenMaxY = cameraPosY + halfWindowTileHeight + 2;

        if(screenMinX < Map.MIN_MAP_SIZE_WIDTH) screenMinX = Map.MIN_MAP_SIZE_WIDTH;
        if(screenMaxX > Map.MAX_MAP_SIZE_WIDTH) screenMaxX = Map.MAX_MAP_SIZE_WIDTH;
        if(screenMinY < Map.MIN_MAP_SIZE_HEIGHT) screenMinY = Map.MIN_MAP_SIZE_HEIGHT;
        if(screenMaxY > Map.MAX_MAP_SIZE_HEIGHT) screenMaxY = Map.MAX_MAP_SIZE_HEIGHT;

        // LAYER 1 - ANIMATED
        map.renderLayer(this.batch, world.getDelta(), 0, screenMinX, screenMaxX, screenMinY, screenMaxY);

        // LAYER 2 - STATIC - FRAMEBUFFERED
        this.batch.draw(map.getBufferedLayer(), 0, 0);
    }

    @Override
    protected void processSystem() {
        this.cameraSystem.camera.update();
        this.batch.setProjectionMatrix(this.cameraSystem.camera.combined);
        this.batch.begin();

        this.renderWorld();

        this.batch.end();
    }


}
