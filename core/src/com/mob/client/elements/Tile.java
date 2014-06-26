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
package com.mob.client.elements;

import com.badlogic.gdx.graphics.Color;
import com.mob.client.Game;
import com.mob.client.sprites.TileSprite;
import com.mob.client.textures.BundledTexture;

public class Tile extends TileSprite {


	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private Character mCharacter;
	private boolean mBlocked;
	private boolean mHasTree;
	private boolean mHasWater;
	private int mLightIndex;
	private int mCharIndex;
	private int mTrigger;

	// ===========================================================
	// Constructors
	// ===========================================================

	public Tile(Game _game, int x, int y, int[] pGraphic, Character pCharacter) {
		super(_game, x, y, pGraphic);
		
		// Plot a character in this tile
		this.setCharacter(pCharacter);
		
		// Check if there is a tree
		if(this.getGrhIndex(2) == 735 || (this.getGrhIndex(2) >= 6994 && this.getGrhIndex(2) <= 7002)) {
			this.mHasTree = true;
		}
		
		// Check if it has water
		if((this.getGrhIndex(0) >= 1505 && this.getGrhIndex(0) <= 1520) || 
		   (this.getGrhIndex(0) >= 5665 && this.getGrhIndex(0) <= 5680) ||
		   (this.getGrhIndex(0) >= 13547 && this.getGrhIndex(0) <= 13562)) {
			if(this.getGrhIndex(1) == 0) this.mHasWater = true;
		}
		
		// Null light
		this.mLightIndex = 0;
	}
	
	public Tile(Game _game, int x, int y, int[] pGraphic) {
		this(_game, x, y, pGraphic, null);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void dispose() {
		if(this.mGraphic != null) {
			for(BundledTexture t : this.mGraphic) {
				if(t != null) t.dispose();
			}
		}
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public boolean isLegalPos() {
		if(this.mBlocked) return false;
		if(this.mCharIndex > 0) return false;
		if(this.mHasWater) return false;
		return true;
	}

	/**
	 * @return returns if we are a roof
	 */
	public boolean isRoof() {
		return (this.mTrigger == 1 || this.mTrigger == 2 || this.mTrigger == 4 || this.mTrigger == 21);
	}
	
	/**
	 * Creates a new light in this tile
	 * @return the new light index
	 */
	public int createLight(Color pColor, float pSize, float pSpeed) {
		return this.mGame.getEngine().getLightHandler().createLight(this.getX(), this.getY(), pColor, pSize);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mCharacter
	 */
	public Character getCharacter() {
		return mCharacter;
	}

	/**
	 * @param mCharacter the mCharacter to set
	 */
	public void setCharacter(Character mCharacter) {
		this.mCharacter = mCharacter;
		if(this.mCharacter != null) 
			this.mCharIndex = this.mCharacter.getCharIndex();
		else
			this.mCharIndex = 0;
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
		
		// Create static bodys for blocked tiles
		if(this.isBlocked()) {
			this.createBody(TILE_PIXEL_WIDTH, TILE_PIXEL_HEIGHT);
		}
				
	}

	/**
	 * @return the mHasTree
	 */
	public boolean hasTree() {
		return mHasTree;
	}

	/**
	 * @param mHasTree the mHasTree to set
	 */
	public void setHasTree(boolean mHasTree) {
		this.mHasTree = mHasTree;
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
	
	/**
	 * @return the mLightIndex
	 */
	public int getLightIndex() {
		return mLightIndex;
	}

	/**
	 * @param mLightIndex the mLightIndex to set
	 */
	public void setLightIndex(int mLightIndex) {
		this.mLightIndex = mLightIndex;
	}

	/**
	 * @return the mHasWater
	 */
	public boolean hasWater() {
		return mHasWater;
	}

	/**
	 * @param mHasWater the mHasWater to set
	 */
	public void setWater(boolean mHasWater) {
		this.mHasWater = mHasWater;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
