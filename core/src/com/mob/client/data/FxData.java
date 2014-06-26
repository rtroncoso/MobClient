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
 * Store information about an FX
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

public class FxData {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private int mGrhIndex;
	private int mOffsetX;
	private int mOffsetY;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * @param mGrhIndex
	 * @param mOffsetX
	 * @param mOffsetY
	 */
	public FxData(int mGrhIndex, int mOffsetX, int mOffsetY) {
		this.mGrhIndex = mGrhIndex;
		this.mOffsetX = mOffsetX;
		this.mOffsetY = mOffsetY;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mGrhIndex
	 */
	public int getGraphic() {
		return mGrhIndex;
	}
	/**
	 * @param mGrhIndex the mGrhIndex to set
	 */
	public void setGraphic(int mGrhIndex) {
		this.mGrhIndex = mGrhIndex;
	}
	
	/**
	 * @return the mOffsetX
	 */
	public int getOffsetX() {
		return mOffsetX;
	}
	/**
	 * @param mOffsetX the mOffsetX to set
	 */
	public void setOffsetX(int mOffsetX) {
		this.mOffsetX = mOffsetX;
	}
	/**
	 * @return the mOffsetY
	 */
	public int getOffsetY() {
		return mOffsetY;
	}
	/**
	 * @param mOffsetY the mOffsetY to set
	 */
	public void setOffsetY(int mOffsetY) {
		this.mOffsetY = mOffsetY;
	}


	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
