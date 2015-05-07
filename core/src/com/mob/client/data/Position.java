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
 * Stores position information
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

import com.mob.client.interfaces.Constants;

public class Position implements Constants {

	private float x;
	private float y;

	public Position() {
		this(0, 0);
	}
	
	public Position(float x, float y) {
		this.setX(x);
		this.setY(y);
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}


	/**
	 * @param mX the x to set
	 */
	public void setX(float mX) {
		this.x = mX;
	}
	
	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}


	/**
	 * @param mY the y to set
	 */
	public void setY(float mY) {
		this.y = mY;
	}


	/**
	 * @return the x
	 */
	public float getTileX() {
		return x * Tile.TILE_PIXEL_WIDTH;
	}


	/**
	 * @param x the x to set
	 */
	public void setTileX(int x) {
		this.x = x * Tile.TILE_PIXEL_WIDTH;
	}
	
	/**
	 * @return the y
	 */
	public float getTileY() {
		return y * Tile.TILE_PIXEL_HEIGHT;
	}


	/**
	 * @param y the y to set
	 */
	public void setTileY(int y) {
		this.y = y * Tile.TILE_PIXEL_HEIGHT;
	}

}
