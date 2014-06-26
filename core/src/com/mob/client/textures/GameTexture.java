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
 * Packages and loads a Texture into a TextureRegion by a given GrhIndex
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.textures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mob.client.Game;
import com.mob.client.data.GrhData;
import com.mob.client.interfaces.Constants;

public class GameTexture implements Constants {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private TextureRegion mTextureRegion;
	private float mX;
	private float mY;

	// ===========================================================
	// Constructors
	// ===========================================================
	public GameTexture(Game _game, int grhIndex) {
		GrhData grh = _game.getGrhData().get(grhIndex);
		
		this.mTextureRegion = new TextureRegion(_game.getSurfaceHandler().get(String.valueOf(grh.getFileNum())), grh.getX(), grh.getY(), grh.getPixelWidth(), grh.getPixelHeight());
		this.mTextureRegion.flip(false, true);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public void dispose() {
		this.mTextureRegion.getTexture().dispose();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mTextureRegion
	 */
	public TextureRegion getGraphic() {
		return mTextureRegion;
	}

	/**
	 * @param mTextureRegion the mTextureRegion to set
	 */
	public void setGraphic(TextureRegion mTextureRegion) {
		this.mTextureRegion = mTextureRegion;
	}

	/**
	 * @return the mX
	 */
	public float getX() {
		return mX;
	}

	/**
	 * @param mX the mX to set
	 */
	public void setX(float mX) {
		this.mX = mX;
	}

	/**
	 * @return the mY
	 */
	public float getY() {
		return mY;
	}

	/**
	 * @param mY the mY to set
	 */
	public void setY(float mY) {
		this.mY = mY;
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================


	  

}
