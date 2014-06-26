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
 * Stores information about a helmet
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

public class HelmetData {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private int[] mHelmetIndex;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * @param headIndex
	 */
	public HelmetData(int[] helmetIndex) {
		super();
		this.mHelmetIndex = helmetIndex;
	}


	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the headIndex
	 */
	public int[] getHelmetIndex() {
		return mHelmetIndex;
	}

	/**
	 * @param headIndex the headIndex to set
	 */
	public void setHelmetIndex(int[] helmetIndex) {
		this.mHelmetIndex = helmetIndex;
	}
	
	public int getHelmet(int pIndex) {
		return this.mHelmetIndex[pIndex];
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================


}
