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
 * Sprite with capabilities to move but no rendering (usually inherited by characters or other moving sprites)
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.sprites;

import com.mob.client.Game;
import com.mob.client.interfaces.Constants;

public abstract class MovingSprite extends GameSprite implements Constants {
	// ===========================================================
	// Constants
	// ===========================================================
	

	// ===========================================================
	// Fields
	// ===========================================================
	protected int mNextX;
	protected int mNextY;
	
	protected boolean mMoving;
	protected float mSpeed;
	
	protected Heading mHeading;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public MovingSprite (Game game, float x, float y) {
		super (game, x, y);
		
		this.mNextX = 0;
		this.mNextY = 0;
		
		this.mMoving = false;
		this.mSpeed = 175.0f; // TODO : don't harcode movement speed
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the _moving
	 */
	public boolean isMoving() {
		return mMoving;
	}

	/**
	 * @param _moving the _moving to set
	 */
	public void setMoving(boolean _moving) {
		this.mMoving = _moving;
	}

	/**
	 * @return the mHeading
	 */
	public int getHeading() {
		return mHeading.toInt();
	}
	
	public Heading getHeadingEnum() {
		return mHeading;
	}

	/**
	 * @param mHeading the mHeading to set
	 */
	public void setHeading(Heading mHeading) {
		this.mHeading = mHeading;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public void move(Heading pHeading) {
		this.mHeading = pHeading;
	}
	
	public void place() {
		
	}
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
