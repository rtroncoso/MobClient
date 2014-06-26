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
 * Now packs everything in GameTextures (previously known as BundledTexture) 
 * treating everything as an animation
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-17
 */
package com.mob.client.textures;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mob.client.Game;
import com.mob.client.data.GrhData;

public class BundledTexture {
	
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private GameTexture[] mFrames;
	private Animation mAnimation;
	private float mAnimationTime;
	private boolean mAnimated;
	private float mX;
	private float mY;

	// ===========================================================
	// Constructors
	// ===========================================================
	public BundledTexture (Game _game, int grhIndex) {
		this(_game, grhIndex, false);
	}
	
	public BundledTexture (Game _game, int grhIndex, boolean pAnimated) {
		this.mAnimationTime = 0.0f;
		
		if(!pAnimated) {
			this.mFrames = new GameTexture[1];
			this.mFrames[0] = new GameTexture(_game, grhIndex);
			this.mAnimated = false;
		} else {
			GrhData grh = _game.getGrhData().get(grhIndex);
			int numFrames = grh.getFrames().length;
			
			this.mFrames = new GameTexture[numFrames];
			TextureRegion tmpFrames[] = new TextureRegion[numFrames];
			for(int i = 0; i < numFrames; i++) {
				this.mFrames[i] = new GameTexture(_game, grh.getFrame(i));
				tmpFrames[i] = this.mFrames[i].getGraphic();
			}
			this.mAnimation = new Animation(grh.getSpeed() / 1000, tmpFrames);
			this.mAnimated = true;
		}
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public void dispose() {
		
		// Dispose all our frames
		for(GameTexture t : this.mFrames) {
			t.dispose();
		}
	}
	
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
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

	/**
	 * @return the mFrames
	 */
	public GameTexture[] getFrames() {
		return mFrames;
	}

	/**
	 * @param mFrames the mFrames to set
	 */
	public void setFrames(GameTexture[] mFrames) {
		this.mFrames = mFrames;
	}
	
	public TextureRegion getGraphic() {
		return this.getGraphic(0);
	}

	
	public TextureRegion getGraphic(int pIndex) {
		return this.mFrames[pIndex].getGraphic();
	}
	
	public TextureRegion getGraphic(boolean loop) {
		if(this.mAnimated)
			return this.mAnimation.getKeyFrame(this.mAnimationTime, loop);
		else
			return this.getGraphic();
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

	/**
	 * @return the mAnimated
	 */
	public boolean isAnimated() {
		return mAnimated;
	}

	/**
	 * @param mAnimated the mAnimated to set
	 */
	public void setAnimated(boolean mAnimated) {
		this.mAnimated = mAnimated;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
}
