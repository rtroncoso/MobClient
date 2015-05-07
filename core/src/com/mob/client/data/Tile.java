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
 * Stores single tile information
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mob.client.handlers.AssetsHandler;
import com.mob.client.textures.BundledAnimation;

import java.util.Vector;

public class Tile {

	public static final float TILE_PIXEL_WIDTH = 32.0f;
	public static final float TILE_PIXEL_HEIGHT = 32.0f;

	private int[] mGraphic;
	private Vector<BundledAnimation> mTextures = new Vector<BundledAnimation>();
	
	private int mCharIndex;
	private int mObjIndex;
	private int mNpcIndex;
	
	private WorldPosition mTileExit;
	private boolean mBlocked;
	
	private int mTrigger;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * @param mGraphic
	 * @param mCharIndex
	 * @param mObjIndex
	 * @param mNpcIndex
	 * @param tileExit
	 * @param mBlocked
	 * @param mTrigger
	 */
	public Tile(int[] mGraphic, int mCharIndex, int mObjIndex,
				int mNpcIndex, WorldPosition tileExit, boolean mBlocked,
				int mTrigger) {
		super();
		this.setGraphic(mGraphic);
		this.setCharIndex(mCharIndex);
		this.setObjIndex(mObjIndex);
		this.setNpcIndex(mNpcIndex);
		this.setTileExit(tileExit);
		this.setBlocked(mBlocked);
		this.setTrigger(mTrigger);
		this.loadTextures();
	}


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public void loadTextures() {
		int layer = 0;
		this.mTextures.setSize(this.getGraphic().length);
		for(int grhIndex : this.getGraphic()) {
			if(grhIndex > 0) this.mTextures.setElementAt(new BundledAnimation(AssetsHandler.getGrh(grhIndex)), layer);
			layer++;
		}
	}


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public TextureRegion getRegion(int pIndex) {
		return (this.getGraphic(pIndex) > 0) ?
				(this.mTextures.get(pIndex).isAnimated() ?
						this.mTextures.get(pIndex).getAnimatedGraphic(true) :
						this.mTextures.get(pIndex).getGraphic()) :
				null;
	}

	public BundledAnimation getAnimation(int pIndex) {
		return (this.mTextures.get(pIndex) != null) ? this.mTextures.get(pIndex) : null;
	}

	public int getGraphic(int pIndex) {
		return this.mGraphic[pIndex];
	}
	/**
	 * @return the mGraphic
	 */
	public int[] getGraphic() {
		return this.mGraphic;
	}

	/**
	 * @param mGraphic the mGraphic to set
	 */
	public void setGraphic(int[] mGraphic) {
		this.mGraphic = mGraphic;
	}


	/**
	 * @return the mCharIndex
	 */
	public int getCharIndex() {
		return mCharIndex;
	}


	/**
	 * @param mCharIndex the mCharIndex to set
	 */
	public void setCharIndex(int mCharIndex) {
		this.mCharIndex = mCharIndex;
	}


	/**
	 * @return the mObjIndex
	 */
	public int getObjIndex() {
		return mObjIndex;
	}


	/**
	 * @param mObjIndex the mObjIndex to set
	 */
	public void setObjIndex(int mObjIndex) {
		this.mObjIndex = mObjIndex;
	}


	/**
	 * @return the mNpcIndex
	 */
	public int getNpcIndex() {
		return mNpcIndex;
	}


	/**
	 * @param mNpcIndex the mNpcIndex to set
	 */
	public void setNpcIndex(int mNpcIndex) {
		this.mNpcIndex = mNpcIndex;
	}


	/**
	 * @return the tileExit
	 */
	public WorldPosition getTileExit() {
		return mTileExit;
	}


	/**
	 * @param tileExit the tileExit to set
	 */
	public void setTileExit(WorldPosition tileExit) {
		this.mTileExit = tileExit;
	}


	/**
	 * @return the mBlocked
	 */
	public boolean isBlocked() {
		return mBlocked;
	}


	/**
	 * @param mBlocked the mBlocked to set
	 */
	public void setBlocked(boolean mBlocked) {
		this.mBlocked = mBlocked;
	}


	/**
	 * @return the mTrigger
	 */
	public int getTrigger() {
		return mTrigger;
	}


	/**
	 * @param mTrigger the mTrigger to set
	 */
	public void setTrigger(int mTrigger) {
		this.mTrigger = mTrigger;
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
