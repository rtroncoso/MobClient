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

public class Fx {

	private int grhIndex;
	private int offsetX;
	private int offsetY;

	/**
	 * @param grhIndex
	 * @param offsetX
	 * @param offsetY
	 */
	public Fx(int grhIndex, int offsetX, int offsetY) {
		this.grhIndex = grhIndex;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

	/**
	 * @return the grhIndex
	 */
	public int getGraphic() {
		return grhIndex;
	}
	/**
	 * @param grhIndex the grhIndex to set
	 */
	public void setGraphic(int grhIndex) {
		this.grhIndex = grhIndex;
	}
	
	/**
	 * @return the offsetX
	 */
	public int getOffsetX() {
		return offsetX;
	}
	/**
	 * @param offsetX the offsetX to set
	 */
	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}
	/**
	 * @return the offsetY
	 */
	public int getOffsetY() {
		return offsetY;
	}
	/**
	 * @param offsetY the offsetY to set
	 */
	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

}
