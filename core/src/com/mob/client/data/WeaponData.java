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
 * Stores weapon data
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

public class WeaponData {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private int[] mGraphic;

	// ===========================================================
	// Constructors
	// ===========================================================
	public WeaponData(int[] pGraphic) {
		this.setGraphicArray(pGraphic);
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
	
	public void setGraphic(int pIndex, int pGraphic) {
		this.mGraphic[pIndex] = pGraphic;
	}
	
	/**
	 * @return the mGraphic
	 */
	public int[] getGraphicArray() {
		return mGraphic;
	}

	/**
	 * @param mGraphic the mGraphic to set
	 */
	public void setGraphicArray(int[] mGraphic) {
		this.mGraphic = mGraphic;
	}


	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
