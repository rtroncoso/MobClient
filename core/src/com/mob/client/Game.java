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

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mob.client.handlers.AssetsHandler;
import com.mob.client.handlers.ScreenHandler;
import com.mob.client.handlers.SurfaceHandler;

public class Game implements ApplicationListener {

    public static final String GAME_GRAPHICS_PATH = "data/graficos/";
    public static final String GAME_FONTS_PATH = "data/fonts/";
    public static final String GAME_GRAPHICS_EXTENSION = ".png";
    public static final String GAME_SHADERS_PATH = "data/shaders/";
    public static final String GAME_SHADERS_LIGHT = "light.png";

    public static final int GAME_SCREEN_WIDTH = 1366;
    public static final int GAME_SCREEN_HEIGHT = 768;
    public static final float GAME_SCREEN_ZOOM = 1.0f;
    public static final boolean GAME_FULL_SCREEN = false;
    public static final boolean GAME_VSYNC_ENABLED = true;

    protected SpriteBatch spriteBatch;

    @Override
    public void create() {

        // Init spritebatch
        this.spriteBatch = new SpriteBatch();

        // Load resources
        SurfaceHandler.setGraphicsPath(GAME_GRAPHICS_PATH);
        AssetsHandler.load();


        // Create Game Screen and present it
        ScreenHandler.load("GameScreen", this);
    }

    @Override
    public void render() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ScreenHandler.getCurrent().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        ScreenHandler.getCurrent().dispose();
    }

    @Override
    public void pause() {
        ScreenHandler.getCurrent().pause();
    }

    @Override
    public void resize(int arg0, int arg1) {
        ScreenHandler.getCurrent().resize(arg0, arg1);
    }

    @Override
    public void resume() {
        ScreenHandler.getCurrent().resume();
    }

	/**
	 * @return the spriteBatch
	 */
	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	/**
	 * @param spriteBatch the spriteBatch to set
	 */
	public void setSpriteBatch(SpriteBatch spriteBatch) {
		this.spriteBatch = spriteBatch;
	}

}  