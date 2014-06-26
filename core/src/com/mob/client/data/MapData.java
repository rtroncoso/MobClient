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
 * Temporal storage for maps info until I make a Map element (to get maps rendered easier)
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

import com.mob.client.interfaces.Constants;

public class MapData implements Constants {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	protected MapBlockData mTile[][];
	protected boolean mLoaded;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * @param mTile
	 */
	public MapData() {
		this.mTile = new MapBlockData[MAX_MAP_SIZE_WIDTH + 1][MAX_MAP_SIZE_HEIGHT + 1];
		this.mLoaded = false;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mTile
	 */
	public MapBlockData[][] getTileArray() {
		return mTile;
	}

	/**
	 * @param mTile the mTile to set
	 */
	public void setTileArray(MapBlockData mTile[][]) {
		this.mTile = mTile;
	}
	
	public MapBlockData getTile(int pX, int pY) {
		return this.mTile[pX][pY];
	}
	
	public void setTile(int pX, int pY, MapBlockData pMapBlock) {
		this.mTile[pX][pY] = pMapBlock;
	}

	public boolean isLoaded() {
		return mLoaded;
	}

	public void setLoaded(boolean pLoaded) {
		this.mLoaded = pLoaded;
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
