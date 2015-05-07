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
import com.mob.client.data.Graphic;
import com.mob.client.handlers.AssetsHandler;
import com.mob.client.handlers.SurfaceHandler;
import com.mob.client.interfaces.Constants;

/**
 * Class GameTexture
 * Este objeto permite una simple implementación e instanciación de
 * cualquier {@link Graphic} encapsulando y cargando su respectiva
 * {@link TextureRegion}
 * 
 * @author Rodrigo
 */
public class GameTexture {

	private TextureRegion textureRegion;

	public GameTexture(int grhIndex) {
		Graphic graphic = AssetsHandler.getGrhData().get(grhIndex);
		
		this.textureRegion = new TextureRegion(SurfaceHandler.get(String.valueOf(graphic.getFileNum())),
				graphic.getX(), graphic.getY(), graphic.getPixelWidth(), graphic.getPixelHeight());
		this.textureRegion.flip(false, true);
	}
	
	public GameTexture(Graphic graphic) {
		this.textureRegion = new TextureRegion(SurfaceHandler.get(String.valueOf(graphic.getFileNum())),
				graphic.getX(), graphic.getY(), graphic.getPixelWidth(), graphic.getPixelHeight());
		this.textureRegion.flip(false, true);
	}

	public void dispose() {
		this.textureRegion.getTexture().dispose();
	}

	/**
	 * @return the textureRegion
	 */
	public TextureRegion getGraphic() {
		return textureRegion;
	}

	/**
	 * @param textureRegion the textureRegion to set
	 */
	public void setGraphic(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
	}

}
