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

public class Grh {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private int mX;
	private int mY;
	private int mFileNum;
	private int mGrhIndex;
	private int mPixelWidth;
	private int mPixelHeight;
	private float mTileWidth;
	private float mTileHeight;
	private int[] mFrames;
	private float mSpeed;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Grh(int x, int y, int fileNum, int pixelWidth, int pixelHeight,
               float tileWidth, float tileHeight, int[] frames, float speed) {
		super();
		this.mX = x;
		this.mY = y;
		this.mFileNum = fileNum;
		this.mPixelWidth = pixelWidth;
		this.mPixelHeight = pixelHeight;
		this.mTileWidth = tileWidth;
		this.mTileHeight = tileHeight;
		this.mFrames = frames;
		this.mSpeed = speed;
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
		return mX;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.mX = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return mY;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.mY = y;
	}

	/**
	 * @return the fileNum
	 */
	public int getFileNum() {
		return mFileNum;
	}

	/**
	 * @param fileNum the fileNum to set
	 */
	public void setFileNum(int fileNum) {
		this.mFileNum = fileNum;
	}

	/**
	 * @return the pixelWidth
	 */
	public int getPixelWidth() {
		return mPixelWidth;
	}

	/**
	 * @param pixelWidth the pixelWidth to set
	 */
	public void setPixelWidth(int pixelWidth) {
		this.mPixelWidth = pixelWidth;
	}

	/**
	 * @return the pixelHeight
	 */
	public int getPixelHeight() {
		return mPixelHeight;
	}

	/**
	 * @param pixelHeight the pixelHeight to set
	 */
	public void setPixelHeight(int pixelHeight) {
		this.mPixelHeight = pixelHeight;
	}

	/**
	 * @return the tileWidth
	 */
	public float getTileWidth() {
		return mTileWidth;
	}

	/**
	 * @param tileWidth the tileWidth to set
	 */
	public void setTileWidth(float tileWidth) {
		this.mTileWidth = tileWidth;
	}

	/**
	 * @return the tileHeight
	 */
	public float getTileHeight() {
		return mTileHeight;
	}

	/**
	 * @param tileHeight the tileHeight to set
	 */
	public void setTileHeight(float tileHeight) {
		this.mTileHeight = tileHeight;
	}

	/**
	 * @return the frames
	 */
	public int[] getFrames() {
		return mFrames;
	}
	
	public int getFrame(int pIndex) {
		return mFrames[pIndex];
	}

	/**
	 * @param frames the frames to set
	 */
	public void setFrames(int[] frames) {
		this.mFrames = frames;
	}

	/**
	 * @return the speed
	 */
	public float getSpeed() {
		return mSpeed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.mSpeed = speed;
	}

	/**
	 * @return the grhIndex
	 */
	public int getGrhIndex() {
		return mGrhIndex;
	}

	/**
	 * @param grhIndex the grhIndex to set
	 */
	public void setGrhIndex(int grhIndex) {
		this.mGrhIndex = grhIndex;
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
