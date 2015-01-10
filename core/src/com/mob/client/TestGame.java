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
package com.mob.client;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mob.client.handlers.AssetsHandler;
import com.mob.client.handlers.CameraHandler;
import com.mob.client.handlers.MapHandler;
import com.mob.client.handlers.SurfaceHandler;
import com.mob.client.interfaces.ConstantsInterface;
import com.mob.client.screens.GameScreen;
import com.mob.client.screens.Screen;

/**
 * @author Rodrigo
 *
 */
public class TestGame extends Game implements ConstantsInterface {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private SpriteBatch mSpriteBatch;
	private Screen mCurrentScreen;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void create() {
		
		// Cargamos resources
		SurfaceHandler.setGraphicsPath(GAME_GRAPHICS_PATH);
		AssetsHandler.load();
		
		// Inicializamos la c�mara
		OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());  
//		camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		camera.position.set(50 * 32.0f, 50 * 32.0f, 0);
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.zoom = 2.0f;
        CameraHandler.setCamera(camera);
		
		// Inicializamos SpriteBatch
		this.mSpriteBatch = new SpriteBatch();
		
		// Setteamos la screen a usar inicialmente
		this.setScreen(new GameScreen(this));
	}
	
	@Override
	public void render() {
		GL20 gl = Gdx.gl;
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mSpriteBatch
	 */
	public SpriteBatch getSpriteBatch() {
		return mSpriteBatch;
	}

	/**
	 * @param mSpriteBatch the mSpriteBatch to set
	 */
	public void setSpriteBatch(SpriteBatch mSpriteBatch) {
		this.mSpriteBatch = mSpriteBatch;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
