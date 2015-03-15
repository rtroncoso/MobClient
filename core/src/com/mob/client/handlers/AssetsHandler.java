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
 * Manages loader objects
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-06-26
 */
package com.mob.client.handlers;

import java.util.Vector;

import com.mob.client.data.*;
import com.mob.client.data.Body;
import com.mob.client.loaders.BodyLoader;
import com.mob.client.loaders.FxLoader;
import com.mob.client.loaders.GrhLoader;
import com.mob.client.loaders.HeadLoader;
import com.mob.client.loaders.HelmetLoader;
import com.mob.client.loaders.ShieldLoader;
import com.mob.client.loaders.WeaponLoader;

public class AssetsHandler {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private static Vector<Grh> mGrh;
	private static Vector<Body> mBody;
	private static Vector<Head> mHead;
	private static Vector<Helmet> mHelmet;
	private static Vector<Weapon> mWeapon;
    private static Vector<Shield> mShield;
    private static Vector<Fx> mFx;


	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public static void load() {

    	// Init our vectors
        mGrh = new Vector<Grh>();
        mBody = new Vector<Body>();
        mHead = new Vector<Head>();
        mHelmet = new Vector<Helmet>();
        mWeapon = new Vector<Weapon>();
        mShield = new Vector<Shield>();
        mFx = new Vector<Fx>();
        
        // Load all INITs
        mGrh = new GrhLoader().load("Graficos.ind");
        mBody = new BodyLoader().load("Personajes.ind");
        mWeapon = new WeaponLoader().load("Armas.dat");
        mShield = new ShieldLoader().load("Escudos.dat");
        mHead = new HeadLoader().load("Cabezas.ind");
        mHelmet = new HelmetLoader().load("Cascos.ind");
        mFx = new FxLoader().load("Fxs.ind");
		
	}
	
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @param pIndex
	 * @return body data of a specified index
	 */
	public static Body getBody(int pIndex) {
		return AssetsHandler.getBodyData().get(pIndex);
	}
	/**
	 * @param pIndex
	 * @return head data of a specified index
	 */
	public static Head getHead(int pIndex) {
		return AssetsHandler.getHeadData().get(pIndex);
	}
	
	/**
	 * @param pIndex
	 * @return grh data of a specified index
	 */
	public static Grh getGrh(int pIndex) {
		return AssetsHandler.getGrhData().get(pIndex);
	}
	
	/**
	 * @return the mGrh
	 */
	public static Vector<Grh> getGrhData() {
		return mGrh;
	}

	/**
	 * @param mGrh the mGrh to set
	 */
	public static void setGrhData(Vector<Grh> mGrh) {
		AssetsHandler.mGrh = mGrh;
	}

	/**
	 * @return the mBody
	 */
	public static Vector<Body> getBodyData() {
		return mBody;
	}

	/**
	 * @param mBody the mBody to set
	 */
	public static void setBodyData(Vector<Body> mBody) {
		AssetsHandler.mBody = mBody;
	}

	/**
	 * @return the mHead
	 */
	public static Vector<Head> getHeadData() {
		return mHead;
	}

	/**
	 * @param mHead the mHead to set
	 */
	public static void setHeadData(Vector<Head> mHead) {
		AssetsHandler.mHead = mHead;
	}

	/**
	 * @return the mHelmet
	 */
	public static Vector<Helmet> getHelmetData() {
		return mHelmet;
	}

	/**
	 * @param mHelmet the mHelmet to set
	 */
	public static void setHelmetData(Vector<Helmet> mHelmet) {
		AssetsHandler.mHelmet = mHelmet;
	}

	/**
	 * @return the mWeapon
	 */
	public static Vector<Weapon> getWeaponData() {
		return mWeapon;
	}

	/**
	 * @param mWeapon the mWeapon to set
	 */
	public static void setWeaponData(Vector<Weapon> mWeapon) {
		AssetsHandler.mWeapon = mWeapon;
	}

	/**
	 * @return the mShield
	 */
	public static Vector<Shield> getShieldData() {
		return mShield;
	}

	/**
	 * @param mShield the mShield to set
	 */
	public static void setShieldData(Vector<Shield> mShield) {
		AssetsHandler.mShield = mShield;
	}

	/**
	 * @return the mFx
	 */
	public static Vector<Fx> getFxData() {
		return mFx;
	}

	/**
	 * @param mFx the mFx to set
	 */
	public static void setFxData(Vector<Fx> mFx) {
		AssetsHandler.mFx = mFx;
	}


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
