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
package com.mob.dao.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.mob.client.interfaces.Constants;
import com.mob.client.textures.BundledAnimation;

public class Map implements Constants {

    public static final float TILE_PIXEL_WIDTH = 32.0f;
    public static final float TILE_PIXEL_HEIGHT = 32.0f;
    public static final int TILE_BUFFER_SIZE = 7;

    public static final int MAX_MAP_SIZE_WIDTH = 100;
    public static final int MIN_MAP_SIZE_WIDTH = 1;
    public static final int MAX_MAP_SIZE_HEIGHT = 100;
    public static final int MIN_MAP_SIZE_HEIGHT = 1;

    public static final int MAP_START_LAYER = 1;
    public static final int MAP_END_LAYER = 4;

	protected Tile tiles[][];
	protected boolean loaded;
    protected Texture bufferedLayer;

	public Map() {
		this.tiles = new Tile[MAX_MAP_SIZE_WIDTH + 1][MAX_MAP_SIZE_HEIGHT + 1];
		this.loaded = false;
	}

	/**
	 * @return the tiles
	 */
	public Tile[][] getTileArray() {
		return tiles;
	}

	/**
	 * @param tiles the tiles to set
	 */
	public void setTileArray(Tile tiles[][]) {
		this.tiles = tiles;
	}
	
	public Tile getTile(int x, int y) {
		return this.tiles[x][y];
	}
	
	public void setTile(int x, int y, Tile tile) {
		this.tiles[x][y] = tile;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean pLoaded) {
		this.loaded = pLoaded;
	}

    public Texture getBufferedLayer() {
        return bufferedLayer;
    }

    public void setBufferedLayer(Texture bufferedLayer) {
        this.bufferedLayer = bufferedLayer;
    }

    /**
     * Initializes map object, renders layer 2 to FrameBuffer and sets
     * it to loaded=true
     */
    public void initialize() {
        this.renderLayerToBuffer(1);
        this.setLoaded(true);
    }

    /**
     * Renders map's first layer to it's internal FrameBuffer Object
     *
     * @return void
     */
    public void renderLayerToBuffer() {
        this.renderLayerToBuffer(0);
    }

    /**
     * Renders a map layer to it's internal FrameBuffer Object
     *
     * @param layer
     * @return void
     */
    public void renderLayerToBuffer(int layer) {

        int width = (int) (Map.MAX_MAP_SIZE_WIDTH * Map.TILE_PIXEL_WIDTH);
        int height = (int) (Map.MAX_MAP_SIZE_HEIGHT * Map.TILE_PIXEL_HEIGHT);

        OrthographicCamera camera = new OrthographicCamera(width, height);
        camera.setToOrtho(true, width, height);

        FrameBuffer fbo = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
        SpriteBatch sb = new SpriteBatch();
        sb.setProjectionMatrix(camera.combined);
        fbo.begin();

        sb.enableBlending();
        Gdx.gl.glBlendFuncSeparate(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA, GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glViewport(0, 0, width, height);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        this.renderLayer(sb, layer);
        sb.end();

        fbo.end();
        this.bufferedLayer = fbo.getColorBufferTexture();
    }

    /**
     * Renders a Map Layer to a specific SpriteBatch
     *
     * @param batch
     * @return void
     */
    public void renderLayer(SpriteBatch batch) {
        this.renderLayer(batch, 0, 0, MIN_MAP_SIZE_WIDTH, MAX_MAP_SIZE_WIDTH, MIN_MAP_SIZE_HEIGHT, MAX_MAP_SIZE_HEIGHT);
    }

    /**
     * Renders a Map Layer to a specific SpriteBatch
     * @param batch
     * @param layer
     */
    public void renderLayer(SpriteBatch batch, int layer) {
        this.renderLayer(batch, 0.0f, layer, MIN_MAP_SIZE_WIDTH, MAX_MAP_SIZE_WIDTH, MIN_MAP_SIZE_HEIGHT, MAX_MAP_SIZE_HEIGHT);
    }

    /**
     * Renders a Map Layer to a specific SpriteBatch
     * @param batch
     * @param layer
     */
    public void renderLayer(SpriteBatch batch, float delta, int layer) {
        this.renderLayer(batch, delta, layer, MIN_MAP_SIZE_WIDTH, MAX_MAP_SIZE_WIDTH, MIN_MAP_SIZE_HEIGHT, MAX_MAP_SIZE_HEIGHT);
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
    public void renderLayer(SpriteBatch batch, float delta, int layer, int minX, int maxX, int minY, int maxY) {

        for(int y = minY; y <= maxY; y++) {
            for(int x = minX; x <= maxX; x++) {

                BundledAnimation animation = this.getTile(x, y).getAnimation(layer);
                TextureRegion tileRegion = this.getTile(x, y).getRegion(layer);

                if(animation != null && animation.isAnimated()) {
                    animation.setAnimationTime(animation.getAnimationTime() + delta);
                }

                if(tileRegion != null) {
                    final float mapPosX = (x * TILE_PIXEL_WIDTH);
                    final float mapPosY = (y * TILE_PIXEL_HEIGHT);
                    final float tileOffsetX = mapPosX - (tileRegion.getRegionWidth() * 0.5f) - (16.0f);
                    final float tileOffsetY = mapPosY - tileRegion.getRegionHeight();

                    batch.draw(tileRegion, tileOffsetX, tileOffsetY);
                }
            }
        }
    }
}
