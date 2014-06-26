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
 * Holder of the Character Sprite data. Texture handling and rendering of the character goes here.
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.mob.client.Game;
import com.mob.client.data.BodyData;
import com.mob.client.data.FxData;
import com.mob.client.data.HeadData;
import com.mob.client.data.HelmetData;
import com.mob.client.data.ShieldData;
import com.mob.client.data.WeaponData;
import com.mob.client.interfaces.Constants;
import com.mob.client.textures.BundledTexture;

public abstract class CharacterSprite extends MovingSprite implements Constants {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	protected BundledTexture[] mBodySkin;
	protected int mBodyGrhIndex;
	protected BundledTexture[] mWeaponSkin;
	protected int mWeaponGrhIndex;
	protected BundledTexture[] mHeadSkin;
	protected int mHeadGrhIndex;
	protected BundledTexture[] mHelmetSkin;
	protected int mHelmetGrhIndex;
	protected BundledTexture[] mShieldSkin;
	protected int mShieldGrhIndex;
	protected BundledTexture mFxSkin;
	protected int mFxGrhIndex;
	protected int mFxOffsetX;
	protected int mFxOffsetY;
	protected long mFxTimer;
	
	protected int mUserPosX;
	protected int mUserPosY;
	
	protected float mDeltaTime;
	
	protected int mHeadOffsetX;
	protected int mHeadOffsetY;

	protected Rectangle mCharacter;
	protected Color mColor;
	
	protected String mName;
	protected BitmapFont mFont;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public CharacterSprite(Game _game, int x, int y, Heading mHeading, int bodyIndex, int weaponIndex, int headIndex, int helmetIndex, int shieldIndex, int fxIndex) {
		super(_game, x , y);
		
		// Init class
		this.mFont = this.mGame.getFont();
		this.mFont.setColor(0.0f, 0.6f, 0.0f, 1.0f);
		this.mHeading = mHeading;
		this.mDeltaTime = 0.0f;
		this.mBodyGrhIndex = 0;
		this.mHeadGrhIndex = 0;
		this.mHelmetGrhIndex = 0;
		this.mWeaponGrhIndex = 0;
		this.mShieldGrhIndex = 0;
		this.mFxGrhIndex = 0;
		this.mFxOffsetX = 0;
		this.mFxOffsetY = 0;
		this.mFxTimer = 0;
		this.mColor = Color.WHITE;
		
		// Check null indexes
		if(helmetIndex == 0) helmetIndex = 2;
		if(weaponIndex == 0) weaponIndex = 2;
		if(shieldIndex == 0) shieldIndex = 2;
		
		// Load graphics
		if(bodyIndex != 0) this.loadBody(this.mGame.getBodyData().get(bodyIndex));
		if(weaponIndex != 0) this.loadWeapon(this.mGame.getWeaponData().get(weaponIndex));
		if(shieldIndex != 0) this.loadShield(this.mGame.getShieldData().get(shieldIndex));
		if(headIndex != 0) this.loadHead(this.mGame.getHeadData().get(headIndex));
		if(helmetIndex != 0) this.loadHelmet(this.mGame.getHelmetData().get(helmetIndex));
		if(fxIndex != 0) this.loadFx(this.mGame.getFxData().get(fxIndex));
		
		// Calculate offsets
		this.mHeadOffsetX = this.mGame.getBodyData().get(bodyIndex).getHeadOffsetX();
		this.mHeadOffsetY = this.mGame.getBodyData().get(bodyIndex).getHeadOffsetY();
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public TextureRegion getBody() {
		if(this.mMoving)
			return this.mBodySkin[this.getHeading()].getGraphic(true);
		else
			return this.mBodySkin[this.getHeading()].getGraphic();
	}
	
	public TextureRegion getWeapon() {
		if(this.mMoving)
			return this.mWeaponSkin[this.getHeading()].getGraphic(true);
		else
			return this.mWeaponSkin[this.getHeading()].getGraphic();
	}
	
	public TextureRegion getShield() {
		if(this.mMoving)
			return this.mShieldSkin[this.getHeading()].getGraphic(true);
		else
			return this.mShieldSkin[this.getHeading()].getGraphic();
	}
	
	public TextureRegion getHead() {
		return this.mHeadSkin[this.getHeading()].getGraphic();
	}
	
	public TextureRegion getHelmet() {
		return this.mHelmetSkin[this.getHeading()].getGraphic();
	}
	
	public TextureRegion getFx() {
		return this.mFxSkin.getGraphic(true);
	}

	public void setBody(int bodyIndex) {
		if(bodyIndex > 0) {
			loadBody(this.mGame.getBodyData().get(bodyIndex));
		} else {
			this.mBodyGrhIndex = 0;
		}
	}
	
	public void setWeapon(int weaponIndex) {
		if(weaponIndex > 0) {
			loadWeapon(this.mGame.getWeaponData().get(weaponIndex));
		} else {
			this.mWeaponGrhIndex = 0;
		}
	}
	
	public void setShield(int shieldIndex) {
		if(shieldIndex > 0) {
			loadShield(this.mGame.getShieldData().get(shieldIndex));
		} else {
			this.mShieldGrhIndex = 0;
		}
	}
	
	public void setHead(int headIndex) {
		if(headIndex > 0) {
			loadHead(this.mGame.getHeadData().get(headIndex));
		} else {
			this.mHeadGrhIndex = 0;
		}
	}
	
	public void setHelmet(int helmetIndex) {
		if(helmetIndex > 0) {
			loadHelmet(this.mGame.getHelmetData().get(helmetIndex));
		} else {
			this.mHelmetGrhIndex = 0;
		}
	}
	
	public void setFx(int fxIndex) {
		if(fxIndex > 0) {
			loadFx(this.mGame.getFxData().get(fxIndex));
			this.mFxTimer = TimeUtils.millis();
		} else {
			this.mFxGrhIndex = 0;
			this.mFxTimer = 0;
		}
	}
	
	/**
	 * @return the mColor
	 */
	public Color getColor() {
		return mColor;
	}

	/**
	 * @param mColor the mColor to set
	 */
	public void setColor(Color mColor) {
		this.mColor = mColor;
	}

	/**
	 * @return the mUserPosX
	 */
	public int getUserPosX() {
		return mUserPosX;
	}

	/**
	 * @param mUserPosX the mUserPosX to set
	 */
	public void setUserPosX(int mUserPosX) {
		this.mX = (mUserPosX * TILE_PIXEL_WIDTH);
		this.mUserPosX = mUserPosX;
	}

	/**
	 * @return the mUserPosY
	 */
	public int getUserPosY() {
		return mUserPosY;
	}

	/**
	 * @param mUserPosY the mUserPosY to set
	 */
	public void setUserPosY(int mUserPosY) {
		this.mY = (mUserPosY * TILE_PIXEL_HEIGHT);
		this.mUserPosY = mUserPosY;
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	public void loadBody(BodyData bodyData) {
		this.mBodyGrhIndex = bodyData.getGraphic(Heading.NORTH.toInt());
		this.mBodySkin = new BundledTexture[4];
		this.mBodySkin[Heading.NORTH.toInt()] = new BundledTexture(this.mGame, bodyData.getGraphic(Heading.NORTH.toInt()), true);
		this.mBodySkin[Heading.SOUTH.toInt()] = new BundledTexture(this.mGame, bodyData.getGraphic(Heading.SOUTH.toInt()), true);
		this.mBodySkin[Heading.WEST.toInt()] = new BundledTexture(this.mGame, bodyData.getGraphic(Heading.WEST.toInt()), true);
		this.mBodySkin[Heading.EAST.toInt()] = new BundledTexture(this.mGame, bodyData.getGraphic(Heading.EAST.toInt()), true);
	}
	
	public void loadWeapon(WeaponData weaponData) {
		this.mWeaponGrhIndex = weaponData.getGraphic(Heading.NORTH.toInt());
		this.mWeaponSkin = new BundledTexture[4];
		this.mWeaponSkin[Heading.NORTH.toInt()] = new BundledTexture(this.mGame, weaponData.getGraphic(Heading.NORTH.toInt()), true);
		this.mWeaponSkin[Heading.SOUTH.toInt()] = new BundledTexture(this.mGame, weaponData.getGraphic(Heading.SOUTH.toInt()), true);
		this.mWeaponSkin[Heading.WEST.toInt()] = new BundledTexture(this.mGame, weaponData.getGraphic(Heading.WEST.toInt()), true);
		this.mWeaponSkin[Heading.EAST.toInt()] = new BundledTexture(this.mGame, weaponData.getGraphic(Heading.EAST.toInt()), true);
	}
	
	public void loadShield(ShieldData shieldData) {
		this.mShieldGrhIndex = shieldData.getGraphic(Heading.NORTH.toInt());
		this.mShieldSkin = new BundledTexture[4];
		this.mShieldSkin[Heading.NORTH.toInt()] = new BundledTexture(this.mGame, shieldData.getGraphic(Heading.NORTH.toInt()), true);
		this.mShieldSkin[Heading.SOUTH.toInt()] = new BundledTexture(this.mGame, shieldData.getGraphic(Heading.SOUTH.toInt()), true);
		this.mShieldSkin[Heading.WEST.toInt()] = new BundledTexture(this.mGame, shieldData.getGraphic(Heading.WEST.toInt()), true);
		this.mShieldSkin[Heading.EAST.toInt()] = new BundledTexture(this.mGame, shieldData.getGraphic(Heading.EAST.toInt()), true);
	}
	
	public void loadHead(HeadData headData) {
		this.mHeadSkin = new BundledTexture[4];
		this.mHeadGrhIndex = headData.getHead(Heading.NORTH.toInt());
		this.mHeadSkin[Heading.NORTH.toInt()] = new BundledTexture(this.mGame, headData.getHead(Heading.NORTH.toInt()));
		this.mHeadSkin[Heading.SOUTH.toInt()] = new BundledTexture(this.mGame, headData.getHead(Heading.SOUTH.toInt()));
		this.mHeadSkin[Heading.WEST.toInt()] = new BundledTexture(this.mGame, headData.getHead(Heading.WEST.toInt()));
		this.mHeadSkin[Heading.EAST.toInt()] = new BundledTexture(this.mGame, headData.getHead(Heading.EAST.toInt()));
	}
	
	public void loadHelmet(HelmetData helmetData) {
		this.mHelmetSkin = new BundledTexture[4];
		this.mHelmetGrhIndex = helmetData.getHelmet(Heading.NORTH.toInt());
		this.mHelmetSkin[Heading.NORTH.toInt()] = new BundledTexture(this.mGame, helmetData.getHelmet(Heading.NORTH.toInt()));
		this.mHelmetSkin[Heading.SOUTH.toInt()] = new BundledTexture(this.mGame, helmetData.getHelmet(Heading.SOUTH.toInt()));
		this.mHelmetSkin[Heading.WEST.toInt()] = new BundledTexture(this.mGame, helmetData.getHelmet(Heading.WEST.toInt()));
		this.mHelmetSkin[Heading.EAST.toInt()] = new BundledTexture(this.mGame, helmetData.getHelmet(Heading.EAST.toInt()));
	}
	
	public void loadFx(FxData fxData) {
		this.mFxSkin = new BundledTexture(this.mGame, fxData.getGraphic(), true);
		this.mFxGrhIndex = fxData.getGraphic();
		this.mFxOffsetX = fxData.getOffsetX();
		this.mFxOffsetY = fxData.getOffsetY();
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
