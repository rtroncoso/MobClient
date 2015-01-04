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

import com.mob.client.data.BodyData;
import com.mob.client.data.FxData;
import com.mob.client.data.GrhData;
import com.mob.client.data.HeadData;
import com.mob.client.data.HelmetData;
import com.mob.client.data.ShieldData;
import com.mob.client.data.WeaponData;
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
	private static Vector<GrhData> mGrhData;
	private static Vector<BodyData> mBodyData;
	private static Vector<HeadData> mHeadData;
	private static Vector<HelmetData> mHelmetData;
	private static Vector<WeaponData> mWeaponData;
    private static Vector<ShieldData> mShieldData;
    private static Vector<FxData> mFxData;


	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public static void load() {

    	// Init our vectors
        mGrhData = new Vector<GrhData>();
        mBodyData = new Vector<BodyData>();
        mHeadData = new Vector<HeadData>();
        mHelmetData = new Vector<HelmetData>();
        mWeaponData = new Vector<WeaponData>();
        mShieldData = new Vector<ShieldData>();
        mFxData = new Vector<FxData>();
        
        // Load all INITs
        mGrhData = new GrhLoader().load("Graficos.ind");
        mBodyData = new BodyLoader().load("Personajes.ind");
        mWeaponData = new WeaponLoader().load("Armas.dat");
        mShieldData = new ShieldLoader().load("Escudos.dat");
        mHeadData = new HeadLoader().load("Cabezas.ind");
        mHelmetData = new HelmetLoader().load("Cascos.ind");
        mFxData = new FxLoader().load("Fxs.ind");
		
	}
	
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mGrhData
	 */
	public static Vector<GrhData> getGrhData() {
		return mGrhData;
	}

	/**
	 * @param mGrhData the mGrhData to set
	 */
	public static void setGrhData(Vector<GrhData> mGrhData) {
		mGrhData = mGrhData;
	}

	/**
	 * @return the mBodyData
	 */
	public static Vector<BodyData> getBodyData() {
		return mBodyData;
	}

	/**
	 * @param mBodyData the mBodyData to set
	 */
	public static void setBodyData(Vector<BodyData> mBodyData) {
		mBodyData = mBodyData;
	}

	/**
	 * @return the mHeadData
	 */
	public static Vector<HeadData> getHeadData() {
		return mHeadData;
	}

	/**
	 * @param mHeadData the mHeadData to set
	 */
	public static void setHeadData(Vector<HeadData> mHeadData) {
		mHeadData = mHeadData;
	}

	/**
	 * @return the mHelmetData
	 */
	public static Vector<HelmetData> getHelmetData() {
		return mHelmetData;
	}

	/**
	 * @param mHelmetData the mHelmetData to set
	 */
	public static void setHelmetData(Vector<HelmetData> mHelmetData) {
		mHelmetData = mHelmetData;
	}

	/**
	 * @return the mWeaponData
	 */
	public static Vector<WeaponData> getWeaponData() {
		return mWeaponData;
	}

	/**
	 * @param mWeaponData the mWeaponData to set
	 */
	public static void setWeaponData(Vector<WeaponData> mWeaponData) {
		mWeaponData = mWeaponData;
	}

	/**
	 * @return the mShieldData
	 */
	public static Vector<ShieldData> getShieldData() {
		return mShieldData;
	}

	/**
	 * @param mShieldData the mShieldData to set
	 */
	public static void setShieldData(Vector<ShieldData> mShieldData) {
		mShieldData = mShieldData;
	}

	/**
	 * @return the mFxData
	 */
	public static Vector<FxData> getFxData() {
		return mFxData;
	}

	/**
	 * @param mFxData the mFxData to set
	 */
	public static void setFxData(Vector<FxData> mFxData) {
		mFxData = mFxData;
	}


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
