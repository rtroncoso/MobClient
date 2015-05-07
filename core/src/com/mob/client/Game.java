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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mob.client.interfaces.Constants;
import com.mob.client.screens.Screen;

import java.util.HashMap;

public abstract class Game implements ApplicationListener, Constants {

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

    protected HashMap<String, Screen> screens;
    protected SpriteBatch spriteBatch;
	protected Screen currentScreen;

    public Game() {
    	this.screens = new HashMap<String, Screen>();
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