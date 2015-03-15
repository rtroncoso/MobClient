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
package com.mob.client.systems.render;

import com.artemis.annotations.Wire;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mob.client.data.Map;
import com.mob.client.systems.camera.CameraSystem;
import com.mob.client.systems.map.TiledMapSystem;

/**
 * MapRenderSystem Class
 *
 * @author rt
 * @package com.mob.client.systems.render
 */
@Wire
public class MapRenderingSystem extends VoidEntitySystem {

    // ===========================================================
    // Constants
    // ===========================================================


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
        // Iteramos todas las layers del map y las renderizamos
        for(int layer = 0; layer < 4; layer++) {

            // Obtenemos los components del map
            Map map = this.mMapSystem.map;

            for(int y = 1; y <= 100; y++) {
                for(int x = 1; x <= 100; x++) {

                    // Obtenemos los values para esta layer
                    TextureRegion tileRegion = map.getTile(x, y).getRegion(layer);

                    // Si tiene region
                    if(tileRegion != null) {
                        // Acomodamos el tile
                        final float mapPosX = (x * 32.0f);
                        final float mapPosY = (y * 32.0f);
                        final float tileOffsetX = mapPosX - (tileRegion.getRegionWidth() * 0.5f) - (16.0f);
                        final float tileOffsetY = mapPosY - tileRegion.getRegionHeight();

                        // Lo dibujamos
                        this.mBatch.draw(tileRegion, tileOffsetX, tileOffsetY);
                    }
                }
            }
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

        // Todas las calls al renderer irian aca
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
