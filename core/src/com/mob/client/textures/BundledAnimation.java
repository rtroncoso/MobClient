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
package com.mob.client.textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mob.client.data.GrhData;
import com.mob.client.handlers.AssetsHandler;

/**
 * @author Rodrigo
 *
 */
public class BundledAnimation {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private Array<GameTexture> mFrames = new Array<GameTexture>();
	private Animation mAnimation;
	private float mAnimationTime;
	private boolean mAnimated = false;

	// ===========================================================
	// Constructors
	// ===========================================================
	
	public BundledAnimation(GrhData grh) {
		
		// Declaramos algunos datos a usar e internals
		int numFrames = grh.getFrames().length;
		Array<TextureRegion> tmpRegions = new Array<TextureRegion>();
		this.setAnimationTime(0.0f);
		
		// Si es una animaci�n
		if(numFrames > 0) {
			
			// Iteramos la lista de frames y los agregamos a nuestro array interno
			for(int frame : grh.getFrames()) {
				
				// Cargamos una nueva GameTexture usando el frame actual
				this.mFrames.add(new GameTexture(AssetsHandler.getGrh(frame)));
				tmpRegions.add(this.mFrames.peek().getGraphic());
			}
			
			// TODO : Manual array conversion por error en toArray de gdxArray
			TextureRegion[] textures = new TextureRegion[tmpRegions.size];
			int index = 0;
			for(TextureRegion tmpTex : tmpRegions) {
				textures[index] = tmpTex;
				index++;
			}
			
			// Setteamos nuestro animation object
			this.setAnimation(new Animation(grh.getSpeed() / 1000.0f, textures));
			this.mAnimated = true;
			
		} else {
			
			// Vamos a usar un solo frame
			this.mFrames.add(new GameTexture(grh));
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mFrames
	 */
	public Array<GameTexture> getFrames() {
		return mFrames;
	}

	/**
	 * @param mFrames the mFrames to set
	 */
	public void setFrames(Array<GameTexture> mFrames) {
		this.mFrames = mFrames;
	}

	/**
	 * @return the mAnimation
	 */
	public Animation getAnimation() {
		return mAnimation;
	}

	/**
	 * @param mAnimation the mAnimation to set
	 */
	public void setAnimation(Animation mAnimation) {
		this.mAnimation = mAnimation;
	}
	
	/**
	 * Obtiene el primer index de nuestra lista de frames
	 * 
	 * @return TextureRegion
	 */
	public TextureRegion getGraphic() {
		return this.isAnimated() ? this.getAnimatedGraphic(false) : this.getGraphic(0);
	}

	/**
	 * Obtenemos un �ndice espec�fico de nuestra lista de frames
	 * 
	 * @param pIndex
	 * @return TextureRegion
	 */
	public TextureRegion getGraphic(int pIndex) {
		return this.mFrames.get(pIndex).getGraphic();
	}
	
	/**
	 * Obtiene un graphic (con o sin animaci�n) de este objeto
	 * 
	 * @param loop 
	 * @return TextureRegion
	 */
	public TextureRegion getAnimatedGraphic(boolean loop) {
		return this.mAnimation.getKeyFrame(this.getAnimationTime(), loop);
	}
	
	/**
	 * Devuelve si est� animado o no
	 * 
	 * @return boolean
	 */
	public boolean isAnimated() {
		return this.mAnimated;
	}

	/**
	 * @return the mAnimationTime
	 */
	public float getAnimationTime() {
		return mAnimationTime;
	}

	/**
	 * @param mAnimationTime the mAnimationTime to set
	 */
	public void setAnimationTime(float mAnimationTime) {
		this.mAnimationTime = mAnimationTime;
	}


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
