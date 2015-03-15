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
package com.mob.client.sprites;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mob.client.Game;
import com.mob.client.interfaces.Constants;

public abstract class GameSprite implements Constants {
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	protected boolean mActive;
	protected boolean mVisible;
	
	protected int mWidth;
	protected int mHeight;
	
	protected float mX;
	protected float mY;
	
	protected Body mBody;
	
	protected Game mGame;

	// ===========================================================
	// Constructors
	// ===========================================================
	public GameSprite(Game _game, float x, float y) {
		this.mGame = _game;
		
		this.mX = x;
		this.mY = y;
		
		this.mActive = true;
		this.mVisible = true;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public abstract void dispose();
	public abstract void reset();
	public abstract void update(float dt);
	public abstract void draw();
	public void show() {
		this.mVisible = true;
	}
	public void hide() {
		this.mVisible = false;
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mActive
	 */
	public boolean isActive() {
		return mActive;
	}

	/**
	 * @param mActive the mActive to set
	 */
	public void setActive(boolean mActive) {
		this.mActive = mActive;
	}

	/**
	 * @return the mVisible
	 */
	public boolean isVisible() {
		return mVisible;
	}

	/**
	 * @param mVisible the mVisible to set
	 */
	public void setVisible(boolean mVisible) {
		this.mVisible = mVisible;
	}

	/**
	 * @param mHeight the mHeight to set
	 */
	public void setHeight(int mHeight) {
		this.mHeight = mHeight;
	}
	
	/**
	 * @return the _x
	 */
	public float getX() {
		return mX;
	}

	/**
	 * @param _x the _x to set
	 */
	public void setX(float _x) {
		this.mX = _x;
	}

	/**
	 * @return the _y
	 */
	public float getY() {
		return mY;
	}

	/**
	 * @param _y the _y to set
	 */
	public void setY(int _y) {
		this.mY = _y;
	}

	/**
	 * @return the mBody
	 */
	public Body getPhysicsBody() {
		return mBody;
	}

	/**
	 * @param mBody the mBody to set
	 */
	public void setPhysicsBody(Body mBody) {
		this.mBody = mBody;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public float right () {
		return mX + mWidth;
	}
	
	public float left () {
		return mX;
	}
	
	public float top () {
		return mY + mHeight;
	}
	
	public float bottom () {
		return mY;
	}
	
	public Rectangle bounds () {
		return new Rectangle(mX + mWidth * 0.2f, mY + mHeight * 0.2f, mWidth * 0.8f, mHeight * 0.8f);
	}
	
	public void createBody(float tilePixelWidth, float tilePixelHeight) {

		// Calculate half tile
		float halfBodyWidth = TILE_PIXEL_WIDTH / 2f, halfBodyHeight = TILE_PIXEL_HEIGHT /2f;
		
		// Create polygonShap
		PolygonShape tileShape = new PolygonShape();
		tileShape.setAsBox(halfBodyWidth, halfBodyHeight);
		
		// Make this body static
		BodyDef tileBodyDef = new BodyDef();
		tileBodyDef.type = BodyType.StaticBody;
		
		// Hold fixturedef
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = tileShape;
		fixtureDef.filter.groupIndex = 0;

		// Create box2d bodies for all layer 3 objects
	    float bodyX = this.mX * TILE_PIXEL_WIDTH - 2;// + halfBodyWidth / 2f;
	    float bodyY = this.mY * TILE_PIXEL_HEIGHT - halfBodyHeight - 1;
	    tileBodyDef.position.set(bodyX, bodyY);
	    Body tileBody = this.mGame.getEngine().getWorld().createBody(tileBodyDef);
	    tileBody.createFixture(fixtureDef);
	    
	    tileShape.dispose();
	}

	
	public void createBody() {
		createBody(this.mWidth, this.mHeight);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
