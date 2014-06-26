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
package com.mob.client.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.utils.TimeUtils;
import com.mob.client.Game;
import com.mob.client.interfaces.Constants;
import com.mob.client.sprites.CharacterSprite;
import com.mob.client.textures.BundledTexture;

public class Character extends CharacterSprite implements Constants {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private boolean mDead;
	private boolean mInvisible;
	private boolean mSeesRoof;
	private boolean mFocused;
	
	private int mLastUserPosX;
	private int mLastUserPosY;
	private int mCharIndex;
	private int mLightIndex;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Character(Game _game, int charIndex, int x, int y, Heading mHeading, int bodyIndex, int weaponIndex, int headIndex) {
		this(_game, charIndex, x, y, mHeading, bodyIndex, weaponIndex, headIndex, 0, 0);
	}
	
	public Character(Game _game, int charIndex, int x, int y, Heading mHeading, int bodyIndex, int weaponIndex, int headIndex, int helmetIndex, int shieldIndex) {
		this(_game, charIndex, x, y, mHeading, bodyIndex, weaponIndex, headIndex, helmetIndex, shieldIndex, 0);
	}
	
	public Character(Game _game, int charIndex, int x, int y, Heading mHeading, int bodyIndex, int weaponIndex, int headIndex, int helmetIndex, int shieldIndex, int fxIndex) {
		super(_game, x, y, mHeading, bodyIndex, weaponIndex, headIndex, helmetIndex, shieldIndex, fxIndex);
		
		
		// Init object
		this.mLastUserPosX = x;
		this.mLastUserPosY = y;
		this.mCharIndex = charIndex;
		this.mDead = false;
		this.mInvisible = false;
		this.mSeesRoof = false;
		this.mFocused = false;
		this.mLightIndex = 0;
		this.mName = "";
		
		// Update user position
		this.setUserPos(x, y);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void update(float dt) {

		// Update internal timers
		this.mDeltaTime = dt;

		// Update sprite Position
		this.place();
		
		// Render sprite
		this.charRender(dt);

		// If moving and focused we set camera position to ourselves
		if(this.mFocused) this.focusCamera();
	}
	
	@Override
	public void dispose() {
		// Dispose body
		if(this.mBodySkin != null) {
			for(BundledTexture t : this.mBodySkin) {
				if(t != null) t.dispose();
			}
		}
		
		// Dispose weapon
		if(this.mWeaponSkin != null) {
			for(BundledTexture t : this.mWeaponSkin) {
				if(t != null) t.dispose();
			}
		}

		// Dispose shield
		if(this.mShieldSkin != null) {
			for(BundledTexture t : this.mShieldSkin) {
				if(t != null) t.dispose();
			}
		}
			
		// Dispose head
		if(this.mHeadSkin != null) {
			for(BundledTexture t : this.mHeadSkin) {
			 t.dispose();
			}
		}
		
		// Dispose helmet
		if(this.mHelmetSkin != null) {
			for(BundledTexture t : this.mHelmetSkin) {
				if(t != null) t.dispose();
			}
		}
		
		// Dispose fx
		if(this.mFxSkin != null) this.mFxSkin.dispose();
	}
	
	/**
	 * Sub CharRender()
	 */
	public void charRender(float dt) {
		
		// Vars
		float bodyPixelOffsetX = 0, bodyPixelOffsetY = 0, weaponPixelOffsetX = 0, weaponPixelOffsetY = 0, headPixelOffsetX = 0, headPixelOffsetY = 0,
				helmetPixelOffsetX = 0, helmetPixelOffsetY = 0, fxPixelOffsetX = 0, fxPixelOffsetY = 0, shieldPixelOffsetX = 0, shieldPixelOffsetY = 0;
		
		// Set our sprite color
		Color oldColor = this.mGame.getSpriteBatch().getColor();
		this.mGame.getSpriteBatch().setColor(this.mColor);
		
		// Calculate offset to draw and update internal timers
		if(this.mBodyGrhIndex > 0) {
			this.mBodySkin[this.mHeading.toInt()].setAnimationTime(this.mBodySkin[this.getHeading()].getAnimationTime() + this.mDeltaTime);
			bodyPixelOffsetX = this.mX - (this.getBody().getRegionWidth() / 2f);
			bodyPixelOffsetY = this.mY - (this.getBody().getRegionHeight());
		}
		
		if(this.mWeaponGrhIndex > 0) {
			this.mWeaponSkin[this.mHeading.toInt()].setAnimationTime(this.mWeaponSkin[this.getHeading()].getAnimationTime() + this.mDeltaTime);
			weaponPixelOffsetX = this.mX - (this.getWeapon().getRegionWidth() / 2f);
			weaponPixelOffsetY = this.mY - this.getWeapon().getRegionHeight();
		}
		
		if(this.mShieldGrhIndex > 0) {
			this.mShieldSkin[this.mHeading.toInt()].setAnimationTime(this.mShieldSkin[this.getHeading()].getAnimationTime() + this.mDeltaTime);
			shieldPixelOffsetX = this.mX - (this.getShield().getRegionWidth() / 2f);
			shieldPixelOffsetY = this.mY - this.getBody().getRegionHeight() - 5;
		}
		
		if(this.mHeadGrhIndex > 0) {
			headPixelOffsetX = this.mX + this.mHeadOffsetX - (this.getHead().getRegionWidth() / 4f) - 4;
			headPixelOffsetY = this.mY + this.mHeadOffsetY - this.getBody().getRegionHeight() - 5;
		}
		
		if(this.mHelmetGrhIndex > 0) {
			helmetPixelOffsetX = this.mX + this.mHeadOffsetX - (this.getHead().getRegionWidth() / 4f) - 4;
			helmetPixelOffsetY = this.mY + this.mHeadOffsetY - this.getBody().getRegionHeight() - OFFSET_HEAD - 4;
		}
		
		if(this.mFxGrhIndex > 0) {

			// Make fxs only last 1 second
			if(TimeUtils.timeSinceMillis(this.mFxTimer) >= 1500) { 
				this.setFx(0);
			}
			
			// Update fx data
			this.mFxSkin.setAnimationTime(this.mFxSkin.getAnimationTime() + this.mDeltaTime * 9.0f);
			fxPixelOffsetX = this.mX - (this.mFxSkin.getGraphic().getRegionWidth() * 0.5f) - this.mFxOffsetX;
			fxPixelOffsetY = this.mY - this.mFxSkin.getGraphic().getRegionHeight() - this.mFxOffsetY;
		}
		
		// Draw our character
		if(this.mHeadGrhIndex > 0) {
			if(this.mVisible) {
				if(this.mShieldGrhIndex > 0 && this.mHeading == Heading.EAST)
					this.mGame.getSpriteBatch().draw(this.getShield(), shieldPixelOffsetX, shieldPixelOffsetY);
				
				if(this.mBodyGrhIndex > 0) 
					this.mGame.getSpriteBatch().draw(this.getBody(), bodyPixelOffsetX, bodyPixelOffsetY);
						
				if(this.mHeadGrhIndex > 0)
					this.mGame.getSpriteBatch().draw(this.getHead(), headPixelOffsetX, headPixelOffsetY);
				
				if(this.mWeaponGrhIndex > 0)
					this.mGame.getSpriteBatch().draw(this.getWeapon(), weaponPixelOffsetX, weaponPixelOffsetY);
				
				if(this.mHelmetGrhIndex > 0)
					this.mGame.getSpriteBatch().draw(this.getHelmet(), helmetPixelOffsetX, helmetPixelOffsetY);
				
				if(this.mShieldGrhIndex > 0 && this.mHeading != Heading.EAST)
					this.mGame.getSpriteBatch().draw(this.getShield(), shieldPixelOffsetX, shieldPixelOffsetY);
			}
		} else { // Draw only body
			if(this.mBodyGrhIndex > 0) 
				this.mGame.getSpriteBatch().draw(this.getBody(), bodyPixelOffsetX, bodyPixelOffsetY);
		}

		// Replace old color
		this.mGame.getSpriteBatch().setColor(oldColor);
		
		// Draw Fx
		if(this.mFxGrhIndex > 0) {
			// Draw fx
			this.mGame.getSpriteBatch().setColor(1.0f, 1.0f, 1.0f, .6f);
			this.mGame.getSpriteBatch().draw(this.getFx(), fxPixelOffsetX, fxPixelOffsetY);
			this.mGame.getSpriteBatch().setColor(oldColor);
		}
		
		// Draw name
		if(this.mName.length() != 0) {

			String line = this.mName + "\n<Clan>";
			float textWidth = this.mFont.getMultiLineBounds(line).width;
			this.mFont.drawMultiLine(this.mGame.getSpriteBatch(), line, this.mX - (textWidth * 0.5f), this.mY, textWidth, HAlignment.CENTER);
		}
	}
	

	@Override
	public void reset() {
		this.dispose();
	}
	
	@Override
	public void draw() {}
	
	@Override
	public void move(Heading pHeading) {
		
		// Vars
		int nextX = 0, nextY = 0;
		if(this.mMoving) return; // Check if we are already moving
		
		// Set Heading
		this.mHeading = pHeading;
		
		// Set up NextY (let place() handle)
		switch(this.mHeading) {
			case NORTH:
				nextY = -1;
				break;
			case EAST:
				nextX = 1;
				break;
			case SOUTH:
				nextY = 1;
				break;
			case WEST:
				nextX = -1;
				break;
		}
		
		// Check if legal pos
		if(!this.mGame.getEngine().getTile(this.mUserPosX + nextX, this.mUserPosY + nextY).isLegalPos()) return;
		
		// Fill destination
		this.mNextX = nextX;
		this.mNextY = nextY;
		
		// Backup old position
		this.mLastUserPosX = this.mUserPosX;
		this.mLastUserPosY = this.mUserPosY;
		
		// Change position
		this.mUserPosX += this.mNextX;
		this.mUserPosY += this.mNextY;
		
		// Set moving
		this.mMoving = true;
	}
	
	@Override
	public void place() {
		
		// Vars
		float offsetCounterX = 0.0f, offsetCounterY = 0.0f;
		
		// Calculate next user position and finish momvement if we reached destination
		if(this.mNextX != 0) {

			// Calculate moving offset
			offsetCounterX = this.mNextX * this.mSpeed * this.mDeltaTime; 
			this.mX += offsetCounterX;

			// Check if we reached destination
			if(this.mNextX == 1) {
				if(this.mX >= (this.mUserPosX) * TILE_PIXEL_WIDTH) {
					this.updateUserPos();
					this.mMoving = false;
					this.mNextX = 0;
				}
			} else if(this.mNextX == -1) {
				if(this.mX <= (this.mUserPosX) * TILE_PIXEL_WIDTH) {
					this.updateUserPos();
					this.mMoving = false;
					this.mNextX = 0;
				}
			}
		}
		
		if(this.mNextY != 0) {
			
			// Calculate moving offset
			offsetCounterY = this.mNextY * this.mSpeed * this.mDeltaTime; 
			this.mY += offsetCounterY;
			
			// Check if we reached destination
			if(this.mNextY == 1) {
				if(this.mY >= (this.mUserPosY) * TILE_PIXEL_HEIGHT) {
					this.updateUserPos();
					this.mMoving = false;
					this.mNextY = 0;
				}
			} else if(this.mNextY == -1) {
				if(this.mY <= (this.mUserPosY) * TILE_PIXEL_HEIGHT) {
					this.updateUserPos();
					this.mMoving = false;
					this.mNextY = 0;
				}
			}
		}
		
		// Update light position
		this.updateLight(this.mX, this.mY);
	}

	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * IMPORTANT: CALL THIS TO CHANGE USER POSITION
	 */
	public void setUserPos(int x, int y) {
		
		// Delete old player position from map
		this.mGame.getEngine().getTile(this.mLastUserPosX, this.mLastUserPosY).setCharacter(null);;
		
		// Change our graphic position
		this.setUserPosX(x);
		this.setUserPosY(y);
		
		// Plot on map
		this.mGame.getEngine().setCharacter(this.mUserPosX, this.mUserPosY, this);
		
		// Check if we see a roof
		if(this.mGame.getEngine().getTile(this.mUserPosX, this.mUserPosY).isRoof())
			this.mSeesRoof = true;
		else
			this.mSeesRoof = false;
	}
	
	/**
	 * Probably going to use it on refresh position (player lagged)
	 */
	public void updateUserPos() {
		this.setUserPos(this.mUserPosX, this.mUserPosY);
	}
	
	/**
	 * Focuses camera to player position and snaps it to the map borders
	 */
	public void focusCamera() {
		
		// Vars
		float halfWindowWidth = 0, halfWindowHeight = 0, newPosX = 0, newPosY = 0;
		
		// Fill vars
		halfWindowWidth = this.mGame.getCamera().viewportWidth * 0.5f;
		halfWindowHeight = this.mGame.getCamera().viewportHeight * 0.5f;
		
		// Do not move camera if in map bounds
		if(this.mX - halfWindowWidth < TILE_PIXEL_WIDTH || this.mX + halfWindowWidth > MAX_MAP_SIZE_WIDTH * TILE_PIXEL_WIDTH)
			newPosX = this.mGame.getCamera().position.x;
		else
			newPosX = this.mX;
		
		if(this.mY - halfWindowHeight < TILE_PIXEL_HEIGHT || this.mY + halfWindowHeight > MAX_MAP_SIZE_HEIGHT * TILE_PIXEL_HEIGHT)
			newPosY = this.mGame.getCamera().position.y;
		else
			newPosY = this.mY;
		
		//Update camera
		this.mGame.getCamera().position.set(newPosX, newPosY, 0);
		this.mGame.getSpriteBatch().setProjectionMatrix(this.mGame.getCamera().combined);
		this.mGame.getCamera().update();
	}
	
	public void moveUp() {
		this.move(Heading.NORTH);
	}
	
	public void moveDown() {
		this.move(Heading.SOUTH);
	}
	
	public void moveLeft() {
		this.move(Heading.WEST);
	}
	
	public void moveRight() {
		this.move(Heading.EAST);
	}
	
	public void createLight(Color pColor, float pSize) {
		this.mLightIndex = this.mGame.getEngine().getLightHandler().createLight(this.mUserPosY, this.mUserPosX, pColor, pSize);
	}
	
	public void updateLight(float pX, float pY) {
		if(this.mLightIndex != 0) this.mGame.getEngine().getLightHandler().moveLight(this.mLightIndex, pX, pY);
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mNombre
	 */
	public String getName() {
		return mName;
	}
	
	/**
	 * @param mNombre the mNombre to set
	 */
	public void setName(String mName) {
		this.mName = mName;
	}

	/**
	 * @return the mCharIndex
	 */
	public int getCharIndex() {
		return mCharIndex;
	}

	/**
	 * @param mCharIndex the mCharIndex to set
	 */
	public void setCharIndex(int mCharIndex) {
		this.mCharIndex = mCharIndex;
	}

	/**
	 * @return the mInvisible
	 */
	public boolean isInvisible() {
		return mInvisible;
	}

	/**
	 * @param mInvisible the mInvisible to set
	 */
	public void setInvisible(boolean mInvisible) {
		this.mInvisible = mInvisible;
	}

	/**
	 * @return the mDead
	 */
	public boolean isDead() {
		return mDead;
	}

	/**
	 * @param mDead the mDead to set
	 */
	public void setDead(boolean mDead) {
		this.mDead = mDead;
	}

	/**
	 * @return the mSeesRoof
	 */
	public boolean isUnderRoof() {
		return mSeesRoof;
	}

	/**
	 * @param mSeesRoof the mSeesRoof to set
	 */
	public void setSeesRoof(boolean mSeesRoof) {
		this.mSeesRoof = mSeesRoof;
	}

	/**
	 * @return the mFocused
	 */
	public boolean isFocused() {
		return mFocused;
	}

	/**
	 * @param mFocused the mFocused to set
	 */
	public void setFocused(boolean mFocused) {
		
		// Set our focus
		this.mFocused = mFocused;
		
		// Fix for new focused characters not drawing (wasn't in old camera offset)
		if(this.mFocused) this.focusCamera();
	}
	
	/**
	 * @return the mLightIndex
	 */
	public int getLight() {
		return mLightIndex;
	}

	/**
	 * @param mLightIndex the mLightIndex to set
	 */
	public void setLight(int mLightIndex) {
		this.mLightIndex = mLightIndex;
	}

	/**
	 * Set focus to this character
	 */
	public void setFocus() {
		this.setFocused(true);
	}
	
	/**
	 * Unset focus
	 */
	public void unsetFocus() {
		this.mFocused = false;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
