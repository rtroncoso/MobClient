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
 * Stores shield data
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.dao.objects;

public class Shield {

	private int[] graphic;

	public Shield(int[] graphic) {
		this.setGraphicArray(graphic);
	}

	public int getGraphic(int index) {
		return this.graphic[index];
	}
	
	public void setGraphic(int index, int graphic) {
		this.graphic[index] = graphic;
	}
	
	/**
	 * @return the graphic
	 */
	public int[] getGraphicArray() {
		return graphic;
	}

	/**
	 * @param graphic the graphic to set
	 */
	public void setGraphicArray(int[] graphic) {
		this.graphic = graphic;
	}

}
