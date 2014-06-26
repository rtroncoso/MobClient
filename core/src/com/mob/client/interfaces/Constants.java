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
 * Game constants and enums
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.interfaces;

import com.badlogic.gdx.graphics.Color;

public interface Constants {

	public static final float PI2 = 3.1415926535897932384626433832795f * 2.0f;
    public static final int GAME_SCREEN_WIDTH = 1366;
    public static final int GAME_SCREEN_HEIGHT = 768;
	public static final float GAME_SCREEN_ZOOM = 1.0f;
	public static final boolean GAME_FULL_SCREEN = false;
	public static final String GAME_GRAPHICS_PATH = "data/graficos/";
	public static final String GAME_GRAPHICS_EXTENSION = ".png";
	public static final String GAME_MAPS_PATH = "data/mapas/";
	public static final String GAME_FONTS_PATH = "data/fonts/";
	public static final String GAME_INIT_PATH = "data/init/";
	public static final String GAME_SHADERS_PATH = "data/shaders/";
	public static final String GAME_SHADERS_LIGHT = "light.png";
    public static final int GAME_FILE_HEADER_SIZE = 263; 
    public static final int GAME_STATE_PLAY = 0;  
    public static final int GAME_STATE_PAUSE = 1;  
    public static final int GAME_STATE_ANIMATE = 2;
    
    public static final float TILE_PIXEL_WIDTH = 32.0f;
    public static final float TILE_PIXEL_HEIGHT = 32.0f;
    public static final int TILE_BUFFER_SIZE = 8;
    
    public static final int MAX_MAP_SIZE_WIDTH = 100;
    public static final int MIN_MAP_SIZE_WIDTH = 1;
    public static final int MAX_MAP_SIZE_HEIGHT = 100;
    public static final int MIN_MAP_SIZE_HEIGHT = 1;
    
    public static final float OFFSET_HEAD = 12.0f;
    
    public static final Color COLOR_DAYLIGHT = new Color(0.5f, 0.5f, 0.5f, 0.2f);
    public static final Color COLOR_DAWN = new Color(0.35f, 0.3f, 0.3f, 0.2f);
    public static final Color COLOR_NIGHT = new Color(0.2f, 0.2f, 0.2f, 1.0f);
    
    public static final float ALPHA_TREES = 1.0f;
    public static final float ALPHA_LIGHTS = 0.4f;
    public static final float ALPHA_FXS = 0.6f;
    
    public static final int DEFAULT_NUM_RAYS = 128;
    
    public enum Heading {
    	NORTH(0), EAST(1), SOUTH(2), WEST(3);
    	
    	final int mHeading;
    	
    	Heading(int pHeading) {
    		this.mHeading = pHeading;
    	}
    	
    	public int toInt() {
    		return this.mHeading;
    	}
    }
    
    
}
