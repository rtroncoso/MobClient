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
 * Contains Box2D methods and main loop of the engine 
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-22
 */
package com.mob.client.engine;


import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mob.client.Game;
import com.mob.client.data.MapBlockData;
import com.mob.client.data.MapData;
import com.mob.client.elements.Tile;
import com.mob.client.handlers.LightHandler;
import com.mob.client.interfaces.Constants;
import com.mob.client.textures.BundledTexture;

public class PhysicsEngine extends Engine implements Constants {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private Game mGame;
	private World mWorld;
	private Box2DDebugRenderer mDebugRenderer;
	private RayHandler mRayHandler;
	private LightHandler mLightHandler;

	// ===========================================================
	// Constructors
	// ===========================================================
	public PhysicsEngine(Game pGame) {
		super(pGame);
		this.mGame = pGame;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public void initEngine() {
		
		// Init box2d world
		this.mWorld = new World(new Vector2(), true);
		this.mDebugRenderer = new Box2DDebugRenderer();
		this.mDebugRenderer.setDrawBodies(false);
		
		// RayHandler setup
		RayHandler.setGammaCorrection(true);
		RayHandler.useDiffuseLight(true);
		this.mRayHandler = new RayHandler(this.mWorld);
		this.mRayHandler.setCulling(true);
		this.mRayHandler.setBlurNum(5);
		
		// LightHandler setup
		this.setLightHandler(new LightHandler(this.mGame));
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void update(float dt)  {
		
		// Vars
		int screenMinX, screenMaxX, screenMinY, screenMaxY, minAreaX, minAreaY, maxAreaX, maxAreaY;
		MapData mapData = this.mGame.getMapHandler().get(this.getMapNumber());
		
		// Calculate visible part of the map
		int cameraPosX = (int) (this.mGame.getCamera().position.x / TILE_PIXEL_WIDTH);
		int cameraPosY = (int) (this.mGame.getCamera().position.y / TILE_PIXEL_HEIGHT);
		int halfWindowTileWidth = (int) ((this.mGame.getCamera().viewportWidth / TILE_PIXEL_WIDTH) / 2f);
		int halfWindowTileHeight = (int) ((this.mGame.getCamera().viewportHeight / TILE_PIXEL_HEIGHT) / 2f);
		
		screenMinX = cameraPosX - halfWindowTileWidth - 1;
		screenMaxX = cameraPosX + halfWindowTileWidth + 1;
		screenMinY = cameraPosY - halfWindowTileHeight - 1;
		screenMaxY = cameraPosY + halfWindowTileHeight + 1;
		
		minAreaX = screenMinX - TILE_BUFFER_SIZE;
		maxAreaX = screenMaxX + TILE_BUFFER_SIZE;
		minAreaY = screenMinY - TILE_BUFFER_SIZE;
		maxAreaY = screenMaxY + TILE_BUFFER_SIZE;
		
		// Make sure it is between map bounds
		if(minAreaX < MIN_MAP_SIZE_WIDTH) minAreaX = MIN_MAP_SIZE_WIDTH;
		if(maxAreaX > MAX_MAP_SIZE_WIDTH) maxAreaX = MAX_MAP_SIZE_WIDTH;
		if(minAreaY < MIN_MAP_SIZE_HEIGHT) minAreaY = MIN_MAP_SIZE_HEIGHT;
		if(maxAreaY > MAX_MAP_SIZE_HEIGHT) maxAreaY = MAX_MAP_SIZE_HEIGHT;
		
		if(screenMinX < MIN_MAP_SIZE_WIDTH) screenMinX = MIN_MAP_SIZE_WIDTH;
		if(screenMaxX > MAX_MAP_SIZE_WIDTH) screenMaxX = MAX_MAP_SIZE_WIDTH;
		if(screenMinY < MIN_MAP_SIZE_HEIGHT) screenMinY = MIN_MAP_SIZE_HEIGHT;
		if(screenMaxY > MAX_MAP_SIZE_HEIGHT) screenMaxY = MAX_MAP_SIZE_HEIGHT;
		
		// Start map render
		/******************************************
		 * Layer 1
		 ******************************************/
		for(int y = screenMinY; y <= screenMaxY; y++) {
			for(int x = screenMinX; x <= screenMaxX; x++) {
				
				Tile tile = this.getTile(x, y);
				BundledTexture layer = tile.getGraphic(0);

				layer.setAnimationTime(layer.getAnimationTime() + dt);

				if(mapData.getTile(x, y).getGraphic()[0] != 0) {
					this.mGame.getSpriteBatch().draw(layer.getGraphic(true), layer.getX(), layer.getY());
				}
			}
		}

		/******************************************
		 * Layer 2
		 ******************************************/
		for(int y = minAreaY; y <= maxAreaY; y++) {
			for(int x = minAreaX; x <= maxAreaX; x++) {
				
				Tile tile = this.getTile(x, y);
				BundledTexture layer = tile.getGraphic(1);
				
				if(mapData.getTile(x, y).getGraphic()[1] != 0) {
					layer.setAnimationTime(layer.getAnimationTime() + dt);
					this.mGame.getSpriteBatch().draw(layer.getGraphic(true), layer.getX(), layer.getY());
				}
			}
		}

		/******************************************
		 * Layer 3
		 ******************************************/
		for(int y = minAreaY; y <= maxAreaY; y++) {
			for(int x = minAreaX; x <= maxAreaX; x++) {
				
				Tile tile = this.getTile(x, y);
				BundledTexture layer = tile.getGraphic(2);

				// Layer 3
				if(mapData.getTile(x, y).getGraphic()[2] != 0) {
					
					layer.setAnimationTime(layer.getAnimationTime() + dt);
					
					// If user is behind a tree draw it with alpha blend
					if(tile.hasTree()) {
						if(Math.abs(this.mGame.getCharacterHandler().getPlayer().getUserPosX() - x) < 4 &&
						   Math.abs(this.mGame.getCharacterHandler().getPlayer().getUserPosY() - y) < 4) {
							Color oldColor = this.mGame.getSpriteBatch().getColor();
							this.mGame.getSpriteBatch().setColor(new Color(oldColor.r, oldColor.g, oldColor.b, ALPHA_TREES));
							this.mGame.getSpriteBatch().draw(layer.getGraphic(), layer.getX(), layer.getY());
							this.mGame.getSpriteBatch().setColor(oldColor);
						} else {
							this.mGame.getSpriteBatch().draw(layer.getGraphic(true), layer.getX(), layer.getY());
						}
					} else {
						this.mGame.getSpriteBatch().draw(layer.getGraphic(true), layer.getX(), layer.getY());
					}
				}
				
				// Character layer
				if(tile.getCharIndex() > 0) {
					tile.getCharacter().update(dt);
				}
			}
		}
		
		/******************************************
		 * Layer 4
		 ******************************************/
		// If user is under a roof we hide it
		if(this.mGame.getCharacterHandler().getPlayer().isUnderRoof()) {
			if(this.mTechoAB > 0) {
				this.mTechoAB -= dt;
			}
			if(this.mTechoAB < 0.05f) this.mTechoAB = 0.0f;
		} else {
			if(this.mTechoAB < 1) {
				this.mTechoAB += dt;
			}
			if(this.mTechoAB > .95f) this.mTechoAB = 1.0f;
		}
		
		for(int y = minAreaY; y <= maxAreaY; y++) {
			for(int x = minAreaX; x <= maxAreaX; x++) {
				
				Tile tile = this.getTile(x, y);
				BundledTexture layer = tile.getGraphic(3);

				if(mapData.getTile(x, y).getGraphic()[3] != 0) {
					layer.setAnimationTime(layer.getAnimationTime() + dt);
					
					// Don't draw the shader above roofs
					Color oldColor = this.mGame.getSpriteBatch().getColor();
					this.mGame.getSpriteBatch().setColor(new Color(oldColor.r, oldColor.g, oldColor.b, this.mTechoAB));
					this.mGame.getSpriteBatch().draw(layer.getGraphic(true), layer.getX(), layer.getY());
					this.mGame.getSpriteBatch().setColor(oldColor);
				}
			}
		}
	}
	
	public void updatePhysics(float dt) {
		// Render box2d world
		this.mWorld.step(1/45f, 6, 2);
		this.mDebugRenderer.render(this.mWorld, this.mGame.getCamera().combined);

		// Render lights
		this.mRayHandler.setAmbientLight(this.mGame.getEngine().getTint().r, this.mGame.getEngine().getTint().g, 
				this.mGame.getEngine().getTint().b, this.mGame.getEngine().getTint().a);
		this.mRayHandler.setCombinedMatrix(this.mGame.getCamera().combined, this.mGame.getCamera().position.x, 
				this.mGame.getCamera().position.y, this.mGame.getCamera().viewportWidth,
				this.mGame.getCamera().viewportHeight);
		this.mRayHandler.updateAndRender();
	}
	
	@Override
	public void reset() {
		Array<Body> tmpBodies = new Array<Body>();
		this.mWorld.getBodies(tmpBodies);
		for(int i = 0; i < tmpBodies.size; i++) {
			tmpBodies.get(i).setActive(false);
			this.mWorld.destroyBody(tmpBodies.get(i));
		}
	}

	@Override
	public void show() {}
	
	@Override
	public void draw() {}
	
	@Override
	public void load() {
		
		// Get mapData
		MapData mapData = this.mGame.getMapHandler().get(this.mMapNumber);
		
		// Clear box2d world
		this.reset();
		
		// Move MapData tiles into our array
		this.mTiles = new Tile[MAX_MAP_SIZE_WIDTH + 1][MAX_MAP_SIZE_HEIGHT + 1];
		for(int y = MIN_MAP_SIZE_HEIGHT; y <= MAX_MAP_SIZE_HEIGHT; y++) {
			for(int x = MIN_MAP_SIZE_WIDTH; x <= MAX_MAP_SIZE_WIDTH; x++) {
				
				MapBlockData tile = mapData.getTile(x, y);
				this.mTiles[x][y] = new Tile(this.mGame, x, y, tile.getGraphic());
				this.mTiles[x][y].setBlocked(tile.isBlocked());
				this.mTiles[x][y].setTrigger(tile.getTrigger());
			}
		}
	}
	
	@Override
	public void dispose() {
		this.mRayHandler.dispose();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mWorld
	 */
	public World getWorld() {
		return mWorld;
	}

	/**
	 * @param mWorld the mWorld to set
	 */
	public void setWorld(World mWorld) {
		this.mWorld = mWorld;
	}

	/**
	 * @return the mLightHandler
	 */
	public LightHandler getLightHandler() {
		return mLightHandler;
	}

	/**
	 * @param mLightHandler the mLightHandler to set
	 */
	public void setLightHandler(LightHandler mLightHandler) {
		this.mLightHandler = mLightHandler;
	}

	/**
	 * @return the mRayHandler
	 */
	public RayHandler getRayHandler() {
		return mRayHandler;
	}

	/**
	 * @param mRayHandler the mRayHandler to set
	 */
	public void setRayHandler(RayHandler mRayHandler) {
		this.mRayHandler = mRayHandler;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
