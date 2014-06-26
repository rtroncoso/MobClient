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
 * Stores information about a body
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

public class BodyData {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private int[] mGrhCuerpo;
	private int mHeadOffsetX;
	private int mHeadOffsetY;

	// ===========================================================
	// Constructors
	// ===========================================================
	/**
	 * @param grhIndex
	 * @param headOffsetX
	 * @param headOffsetY
	 */
	public BodyData(int[] grhIndex, int headOffsetX, int headOffsetY) {
		this.mGrhCuerpo = grhIndex;
		this.mHeadOffsetX = headOffsetX;
		this.mHeadOffsetY = headOffsetY;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public int getGraphic(int pIndex) {
		return this.mGrhCuerpo[pIndex];
	}
	
	public void setGraphic(int pIndex, int pGraphic) {
		this.mGrhCuerpo[pIndex] = pGraphic;
	}
	
	/**
	 * @return the grhIndex
	 */
	public int[] getBodyArray() {
		return mGrhCuerpo;
	}

	/**
	 * @param grhIndex the grhIndex to set
	 */
	public void setBodyArray(int[] grhCuerpo) {
		this.mGrhCuerpo = grhCuerpo;
	}

	/**
	 * @return the headOffsetX
	 */
	public int getHeadOffsetX() {
		return mHeadOffsetX;
	}

	/**
	 * @param headOffsetX the headOffsetX to set
	 */
	public void setHeadOffsetX(int headOffsetX) {
		this.mHeadOffsetX = headOffsetX;
	}

	/**
	 * @return the headOffsetY
	 */
	public int getHeadOffsetY() {
		return mHeadOffsetY;
	}

	/**
	 * @param headOffsetY the headOffsetY to set
	 */
	public void setHeadOffsetY(int headOffsetY) {
		this.mHeadOffsetY = headOffsetY;
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
}
