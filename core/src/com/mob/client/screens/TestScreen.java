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
package com.mob.client.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.mob.client.Game;
import com.mob.client.interfaces.Constants;


public class TestScreen extends Screen implements Constants {
	
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private int map;
	
	// Encapsulate this
	final String mDefaultShaderFile = new FileHandle("data/shaders/defaultPixelShader.glsl").readString();
	final String mLightShaderFile =  new FileHandle("data/shaders/pixelShader.glsl").readString();
	final String mVertexShader = new FileHandle("data/shaders/vertexShader.glsl").readString();
	
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public TestScreen (Game game) {
		super(game);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void createScreen() {
		
		// Init box2d Engine
		this.mGame.getEngine().initEngine();
		
		// Load a map
		this.map = 1;
		this.mGame.getEngine().setMap(this.map);
		this.mGame.getEngine().setTint(COLOR_NIGHT);
		
		// Plot a character
		this.mGame.getCharacterHandler().makeChar(1, 51, 50, Heading.SOUTH, 1, 13, 6, 4, 6);
		this.mGame.getCharacterHandler().getPlayer().setName("BetaTester");
		this.mGame.getCharacterHandler().getPlayer().createLight(Color.WHITE, 300.0f);
		this.mGame.getCharacterHandler().getPlayer().setFocus();
		
		// Plot a npc
		this.mGame.getCharacterHandler().makeChar(2, 50, 50, Heading.SOUTH, 17, 0, 0, 0, 0);
		
		this.mInputMultiplexer = new InputMultiplexer();
		this.mInputMultiplexer.addProcessor(new InputProcessor() {

			@Override
			public boolean keyDown(int keycode) {
				switch(keycode) {
				case(Keys.SPACE):
					mGame.getCharacterHandler().getPlayer().setFx(13);
					break;
				case(Keys.NUM_1):
					mGame.getEngine().setTint(COLOR_NIGHT);
					mGame.getCharacterHandler().getPlayer().unsetFocus();
					break;
				case(Keys.NUM_2):
					mGame.getEngine().setTint(COLOR_DAWN);
					break;
				case(Keys.NUM_3):
					mGame.getEngine().setTint(COLOR_DAYLIGHT);
					break;
				case(Keys.PLUS):
					map += 1;
					mGame.getEngine().setMap(map);
					mGame.getCharacterHandler().getPlayer().updateUserPos();
					break;
				case(Keys.MINUS):
					map -= 1;
					mGame.getEngine().setMap(map);
					mGame.getCharacterHandler().getPlayer().updateUserPos();
					break;
				}
				return true;
			}

			@Override
			public boolean keyUp(int keycode) {
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer,
					int button) {
				if(button == Input.Buttons.LEFT) {
				//mGame.getEngine().getLightHandler().createLight((int) ((mGame.getCamera().position.x - (mGame.getCamera().viewportWidth / 2)) / TILE_PIXEL_WIDTH) + (screenX / TILE_PIXEL_WIDTH), 
							//(int) ((mGame.getCamera().position.y  - (mGame.getCamera().viewportHeight / 2)) / TILE_PIXEL_HEIGHT) + (screenY / TILE_PIXEL_HEIGHT), Color.GREEN, 4f);
				} 
				return true;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer,
					int button) {
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				return false;
			}
			
		});
		Gdx.input.setInputProcessor(this.mInputMultiplexer);
	}

	@Override
	public void update(float dt) {
		
		Gdx.graphics.setTitle("FPS: " + String.valueOf(Gdx.graphics.getFramesPerSecond()
				+ " Map: " + map
				+ " X: " + this.mGame.getCharacterHandler().getPlayer().getUserPosX()
				+ " Y: " + this.mGame.getCharacterHandler().getPlayer().getUserPosY()));
		
		// Input detection
		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			this.mGame.getCharacterHandler().getPlayer().moveUp();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			this.mGame.getCharacterHandler().getPlayer().moveLeft();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			this.mGame.getCharacterHandler().getPlayer().moveDown();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			this.mGame.getCharacterHandler().getPlayer().moveRight();
		}
		
		// Clean up Scene
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
		
        // Start our spritebatch
		this.mGame.getSpriteBatch().begin();

			// Set up camera
			this.mGame.getSpriteBatch().setProjectionMatrix(this.mGame.getCamera().combined);
			this.mGame.getCamera().update();
			
			// Render our engine
        	this.mGame.getEngine().update(dt);
        	
		this.mGame.getSpriteBatch().end();
		
		// Render Box2D Engine
		this.mGame.getEngine().updatePhysics(dt);
	}
	
	@Override
	public void resize(int arg0, int arg1) {
		
	}
	
	@Override
	public void dispose() {
		this.mGame.getEngine().dispose();
		this.mGame.getCharacterHandler().disposeAll();
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void render(float delta) {
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	
	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	
}