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

public class DataHandler {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	private Vector<GrhData> mGrhData;
	private Vector<BodyData> mBodyData;
	private Vector<HeadData> mHeadData;
	private Vector<HelmetData> mHelmetData;
	private Vector<WeaponData> mWeaponData;
    private Vector<ShieldData> mShieldData;
    private Vector<FxData> mFxData;


	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * Constructor
	 */
	public DataHandler() {

    	// Init our vectors
        this.mGrhData = new Vector<GrhData>();
        this.mBodyData = new Vector<BodyData>();
        this.mHeadData = new Vector<HeadData>();
        this.mHelmetData = new Vector<HelmetData>();
        this.mWeaponData = new Vector<WeaponData>();
        this.mShieldData = new Vector<ShieldData>();
        this.mFxData = new Vector<FxData>();
        
        // Load all INITs
        this.mGrhData = new GrhLoader().load("Graficos.ind");
        this.mBodyData = new BodyLoader().load("Personajes.ind");
        this.mWeaponData = new WeaponLoader().load("Armas.dat");
        this.mShieldData = new ShieldLoader().load("Escudos.dat");
        this.mHeadData = new HeadLoader().load("Cabezas.ind");
        this.mHelmetData = new HelmetLoader().load("Cascos.ind");
        this.mFxData = new FxLoader().load("Fxs.ind");
	}

	// ===========================================================
	// Methods
	// ===========================================================
	
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================


	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mGrhData
	 */
	public Vector<GrhData> getGrhData() {
		return mGrhData;
	}

	/**
	 * @param mGrhData the mGrhData to set
	 */
	public void setGrhData(Vector<GrhData> mGrhData) {
		this.mGrhData = mGrhData;
	}

	/**
	 * @return the mBodyData
	 */
	public Vector<BodyData> getBodyData() {
		return mBodyData;
	}

	/**
	 * @param mBodyData the mBodyData to set
	 */
	public void setBodyData(Vector<BodyData> mBodyData) {
		this.mBodyData = mBodyData;
	}

	/**
	 * @return the mHeadData
	 */
	public Vector<HeadData> getHeadData() {
		return mHeadData;
	}

	/**
	 * @param mHeadData the mHeadData to set
	 */
	public void setHeadData(Vector<HeadData> mHeadData) {
		this.mHeadData = mHeadData;
	}

	/**
	 * @return the mHelmetData
	 */
	public Vector<HelmetData> getHelmetData() {
		return mHelmetData;
	}

	/**
	 * @param mHelmetData the mHelmetData to set
	 */
	public void setHelmetData(Vector<HelmetData> mHelmetData) {
		this.mHelmetData = mHelmetData;
	}

	/**
	 * @return the mWeaponData
	 */
	public Vector<WeaponData> getWeaponData() {
		return mWeaponData;
	}

	/**
	 * @param mWeaponData the mWeaponData to set
	 */
	public void setWeaponData(Vector<WeaponData> mWeaponData) {
		this.mWeaponData = mWeaponData;
	}

	/**
	 * @return the mShieldData
	 */
	public Vector<ShieldData> getShieldData() {
		return mShieldData;
	}

	/**
	 * @param mShieldData the mShieldData to set
	 */
	public void setShieldData(Vector<ShieldData> mShieldData) {
		this.mShieldData = mShieldData;
	}

	/**
	 * @return the mFxData
	 */
	public Vector<FxData> getFxData() {
		return mFxData;
	}

	/**
	 * @param mFxData the mFxData to set
	 */
	public void setFxData(Vector<FxData> mFxData) {
		this.mFxData = mFxData;
	}


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
