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

public class MapBlockData {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private int[] mGraphic;
	
	private int mCharIndex;
	private int mObjIndex;
	private int mNpcIndex;
	
	private WorldPositionData mTileExit;
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
	public MapBlockData(int[] mGraphic, int mCharIndex, int mObjIndex,
			int mNpcIndex, WorldPositionData tileExit, boolean mBlocked,
			int mTrigger) {
		super();
		this.setGraphic(mGraphic);
		this.setCharIndex(mCharIndex);
		this.setObjIndex(mObjIndex);
		this.setNpcIndex(mNpcIndex);
		this.setTileExit(tileExit);
		this.setBlocked(mBlocked);
		this.setTrigger(mTrigger);
	}


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
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
	public WorldPositionData getTileExit() {
		return mTileExit;
	}


	/**
	 * @param tileExit the tileExit to set
	 */
	public void setTileExit(WorldPositionData tileExit) {
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
