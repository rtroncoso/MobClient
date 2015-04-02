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
/**
 * Temporal storage for maps info until I make a Map element (to get maps rendered easier)
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.mob.client.interfaces.Constants;

public class Map implements Constants {

	// ===========================================================
	// Constants
	// ===========================================================
    public static final float TILE_PIXEL_WIDTH = 32.0f;
    public static final float TILE_PIXEL_HEIGHT = 32.0f;
    public static final int TILE_BUFFER_SIZE = 7;

    public static final int MAX_MAP_SIZE_WIDTH = 100;
    public static final int MIN_MAP_SIZE_WIDTH = 1;
    public static final int MAX_MAP_SIZE_HEIGHT = 100;
    public static final int MIN_MAP_SIZE_HEIGHT = 1;


	// ===========================================================
	// Fields
	// ===========================================================
	protected MapBlock mTiles[][];
	protected boolean mLoaded;
    protected Texture mBufferedLayer;

// ===========================================================
	// Constructors
	// ===========================================================
	public Map() {
		this.mTiles = new MapBlock[MAX_MAP_SIZE_WIDTH + 1][MAX_MAP_SIZE_HEIGHT + 1];
		this.mLoaded = false;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mTiles
	 */
	public MapBlock[][] getTileArray() {
		return mTiles;
	}

	/**
	 * @param mTiles the mTiles to set
	 */
	public void setTileArray(MapBlock mTiles[][]) {
		this.mTiles = mTiles;
	}
	
	public MapBlock getTile(int pX, int pY) {
		return this.mTiles[pX][pY];
	}
	
	public void setTile(int pX, int pY, MapBlock pMapBlock) {
		this.mTiles[pX][pY] = pMapBlock;
	}

	public boolean isLoaded() {
		return mLoaded;
	}

	public void setLoaded(boolean pLoaded) {
		this.mLoaded = pLoaded;
	}

    public Texture getBufferedLayer() {
        return mBufferedLayer;
    }

    public void setBufferedLayer(Texture mBufferedLayer) {
        this.mBufferedLayer = mBufferedLayer;
    }

	// ===========================================================
	// Methods
	// ===========================================================
    public void initialize() {
        this.renderLayerToBuffer();
        this.setLoaded(true);
    }

    /**
     * Renders a map layer to it's internal FrameBuffer Object
     */
    public void renderLayerToBuffer() {
        this.renderLayerToBuffer(0);
    }

    /**
     * Renders a map layer to it's internal FrameBuffer Object
     * @param layer
     */
    public void renderLayerToBuffer(int layer) {

//        int width = (int) (Map.MAX_MAP_SIZE_WIDTH * Map.TILE_PIXEL_WIDTH);
//        int height = (int) (Map.MAX_MAP_SIZE_HEIGHT * Map.TILE_PIXEL_HEIGHT);
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        FrameBuffer fbo = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
        SpriteBatch sb = new SpriteBatch();

        fbo.begin();
        sb.enableBlending();
        Gdx.gl.glBlendFuncSeparate(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA, GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        this.renderLayer(sb);
        sb.end();

        fbo.end();

        this.mBufferedLayer = fbo.getColorBufferTexture();
    }

    /**
     * Renders a Map Layer to a specific SpriteBatch
     * @param batch
     */
    public void renderLayer(SpriteBatch batch) {
        this.renderLayer(batch, 0, MIN_MAP_SIZE_WIDTH, MAX_MAP_SIZE_WIDTH, MIN_MAP_SIZE_HEIGHT, MAX_MAP_SIZE_HEIGHT);
    }

    /**
     * Renders a Map Layer to a specific SpriteBatch
     * @param batch
     * @param layer
     */
    public void renderLayer(SpriteBatch batch, int layer) {
        this.renderLayer(batch, layer, MIN_MAP_SIZE_WIDTH, MAX_MAP_SIZE_WIDTH, MIN_MAP_SIZE_HEIGHT, MAX_MAP_SIZE_HEIGHT);
    }

    /**
     * Renders a Map Layer to a specific SpriteBatch
     * @param batch
     * @param layer
     * @param minX
     * @param maxX
     * @param minY
     * @param maxY
     */
    public void renderLayer(SpriteBatch batch, int layer, int minX, int maxX, int minY, int maxY) {

        for(int y = minY; y <= maxY; y++) {
            for(int x = minX; x <= maxX; x++) {

                // Obtenemos los values para esta layer
                TextureRegion tileRegion = this.getTile(x, y).getRegion(layer);

                // Si tiene region
                if(tileRegion != null) {
                    // Acomodamos el tile
                    final float mapPosX = (x * TILE_PIXEL_WIDTH);
                    final float mapPosY = (y * TILE_PIXEL_HEIGHT);
                    final float tileOffsetX = mapPosX - (tileRegion.getRegionWidth() * 0.5f) - (16.0f);
                    final float tileOffsetY = mapPosY - tileRegion.getRegionHeight();

                    // Lo dibujamos
                    batch.draw(tileRegion, tileOffsetX, tileOffsetY);
                }
            }
        }
    }

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
