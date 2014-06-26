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
 * Stores a game graphic loaded from the INIT files
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.data;

public class GrhData {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private int x;
	private int y;
	private int fileNum;
	private int grhIndex;
	private int pixelWidth;
	private int pixelHeight;
	private float tileWidth;
	private float tileHeight;
	private int[] frames;
	private float speed;

	// ===========================================================
	// Constructors
	// ===========================================================
	public GrhData(int x, int y, int fileNum, int pixelWidth, int pixelHeight,
			float tileWidth, float tileHeight, int[] frames, float speed) {
		super();
		this.x = x;
		this.y = y;
		this.fileNum = fileNum;
		this.pixelWidth = pixelWidth;
		this.pixelHeight = pixelHeight;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.frames = frames;
		this.speed = speed;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the fileNum
	 */
	public int getFileNum() {
		return fileNum;
	}

	/**
	 * @param fileNum the fileNum to set
	 */
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}

	/**
	 * @return the pixelWidth
	 */
	public int getPixelWidth() {
		return pixelWidth;
	}

	/**
	 * @param pixelWidth the pixelWidth to set
	 */
	public void setPixelWidth(int pixelWidth) {
		this.pixelWidth = pixelWidth;
	}

	/**
	 * @return the pixelHeight
	 */
	public int getPixelHeight() {
		return pixelHeight;
	}

	/**
	 * @param pixelHeight the pixelHeight to set
	 */
	public void setPixelHeight(int pixelHeight) {
		this.pixelHeight = pixelHeight;
	}

	/**
	 * @return the tileWidth
	 */
	public float getTileWidth() {
		return tileWidth;
	}

	/**
	 * @param tileWidth the tileWidth to set
	 */
	public void setTileWidth(float tileWidth) {
		this.tileWidth = tileWidth;
	}

	/**
	 * @return the tileHeight
	 */
	public float getTileHeight() {
		return tileHeight;
	}

	/**
	 * @param tileHeight the tileHeight to set
	 */
	public void setTileHeight(float tileHeight) {
		this.tileHeight = tileHeight;
	}

	/**
	 * @return the frames
	 */
	public int[] getFrames() {
		return frames;
	}
	
	public int getFrame(int pIndex) {
		return frames[pIndex];
	}

	/**
	 * @param frames the frames to set
	 */
	public void setFrames(int[] frames) {
		this.frames = frames;
	}

	/**
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * @return the grhIndex
	 */
	public int getGrhIndex() {
		return grhIndex;
	}

	/**
	 * @param grhIndex the grhIndex to set
	 */
	public void setGrhIndex(int grhIndex) {
		this.grhIndex = grhIndex;
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
