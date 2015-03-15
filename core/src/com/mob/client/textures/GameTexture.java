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
import com.mob.client.data.Grh;
import com.mob.client.handlers.SurfaceHandler;
import com.mob.client.interfaces.Constants;

/**
 * Este objeto permite una simple implementaci�n e instanciaci�n de 
 * cualquier {@link com.mob.client.data.Grh} encapsulando y cargando su respectiva
 * {@link TextureRegion}
 * 
 * @author Rodrigo
 *
 */
public class GameTexture implements Constants {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private TextureRegion mTextureRegion;

	// ===========================================================
	// Constructors
	// ===========================================================
	public GameTexture(Game _game, int grhIndex) {
		Grh grh = _game.getGrhData().get(grhIndex);
		
		this.mTextureRegion = new TextureRegion(SurfaceHandler.get(String.valueOf(grh.getFileNum())), grh.getX(), grh.getY(), grh.getPixelWidth(), grh.getPixelHeight());
		this.mTextureRegion.flip(false, true);
	}
	
	public GameTexture(Grh grh) {
		this.mTextureRegion = new TextureRegion(SurfaceHandler.get(String.valueOf(grh.getFileNum())), grh.getX(), grh.getY(), grh.getPixelWidth(), grh.getPixelHeight());
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

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================


	  

}
