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
 * Handles all active textures loaded from the graphics path.
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.shared.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.mob.client.Game;
import com.mob.client.interfaces.Constants;

public class SurfaceHandler implements Constants {
	
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private static HashMap<String, Texture> mDict = new HashMap<String, Texture>();
	private static String mGraphicsPath;
	
	protected Game mGame;

	// ===========================================================
	// Constructors
	// ===========================================================
	public SurfaceHandler(Game game) {
		this.mGame = game;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mGraphicsPath
	 */
	public static String getGraphicsPath() {
		return mGraphicsPath;
	}

	/**
	 * @param mGraphicsPath the mGraphicsPath to set
	 */
	public static void setGraphicsPath(String mGraphicsPath) {
		SurfaceHandler.mGraphicsPath = mGraphicsPath;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * Loads into memory ALL the textures in the graphics path
	 */
	public static void loadAllTextures() {
		FileHandle file = Gdx.app.getFiles().internal(SurfaceHandler.mGraphicsPath);
		if(file.isDirectory()) {
			for(FileHandle tmp : file.list()) {
				if(tmp.extension() == Game.GAME_GRAPHICS_EXTENSION) {
					Gdx.app.log(SurfaceHandler.class.getSimpleName(), "Cargando " + tmp.name());
					SurfaceHandler.loadTexture(tmp.nameWithoutExtension());
				}
			}
		}
	}
	
	/**
	 * @param fileName Name of the file found in the graphics folder
	 */
	public static void loadTexture(String fileName) {
		Texture texture = new Texture(SurfaceHandler.mGraphicsPath + fileName + Game.GAME_GRAPHICS_EXTENSION);
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		SurfaceHandler.add(fileName, texture);
	}

	/**
	 * @param key Index in map
	 * @param texture Texture to add
	 */
	 public static void add(final String key, final Texture texture) {
		 if (SurfaceHandler.mDict.containsKey(key)) {
			 return;
		 }
	   
		 SurfaceHandler.mDict.put(key, texture);
	 }

	/**
	 * Gets a Texture from the map
	 * @param key Index in map
	 * @return BundledTexture inside the map
	 */
	 public static Texture get(final String key) {
		 if(!SurfaceHandler.mDict.containsKey(key)) SurfaceHandler.loadTexture(key);
		 return SurfaceHandler.mDict.get(key);
	 }
	  
	 /**
	  * Unloads a Texture from the map and memory
	  * @param key Index in map
	  */
	public static void dispose(final String key) {
		if (!SurfaceHandler.mDict.containsKey(key)) {
			return;
		}
	   
		final Texture t = SurfaceHandler.mDict.get(key);
		t.dispose();
		SurfaceHandler.mDict.remove(key);
	}
	  
	public static void disposeAll() {
		for (final Texture t : SurfaceHandler.mDict.values()) {
			t.dispose();
		}
   
		SurfaceHandler.mDict.clear();
	 }

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================


}
