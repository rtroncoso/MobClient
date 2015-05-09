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
package com.mob.shared.handlers;

import java.util.Vector;

import com.mob.shared.data.*;
import com.mob.shared.data.Body;
import com.mob.shared.loaders.FxLoader;
import com.mob.shared.loaders.GraphicLoader;
import com.mob.shared.loaders.HeadLoader;
import com.mob.shared.loaders.HelmetLoader;
import com.mob.shared.loaders.ShieldLoader;
import com.mob.shared.loaders.WeaponLoader;
import com.mob.shared.readers.*;

public class AssetsHandler {

	private static Vector<Graphic> graphic;
	private static Vector<Body> body;
	private static Vector<Head> head;
	private static Vector<Helmet> helmet;
	private static Vector<Weapon> weapon;
    private static Vector<Shield> shield;
    private static Vector<Fx> fx;

	public static void load() {

    	// Init our vectors
        graphic = new Vector<Graphic>();
        body = new Vector<Body>();
        head = new Vector<Head>();
        helmet = new Vector<Helmet>();
        weapon = new Vector<Weapon>();
        shield = new Vector<Shield>();
        fx = new Vector<Fx>();
        
        // Load all INITs
        graphic = new GraphicReader("Graficos").read();
        body = new BodyReader("Personajes").read();
        weapon = new WeaponReader("Armas").read();
        shield = new ShieldReader("Escudos").read();
        head = new HeadReader("Cabezas").read();
        helmet = new HelmetReader("Cascos").read();
        fx = new FxReader("Fxs").read();
		
	}

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
	public static Graphic getGrh(int pIndex) {
		return AssetsHandler.getGrhData().get(pIndex);
	}
	
	/**
	 * @return the graphic
	 */
	public static Vector<Graphic> getGrhData() {
		return graphic;
	}

	/**
	 * @param mGraphic the graphic to set
	 */
	public static void setGrhData(Vector<Graphic> mGraphic) {
		AssetsHandler.graphic = mGraphic;
	}

	/**
	 * @return the body
	 */
	public static Vector<Body> getBodyData() {
		return body;
	}

	/**
	 * @param mBody the body to set
	 */
	public static void setBodyData(Vector<Body> mBody) {
		AssetsHandler.body = mBody;
	}

	/**
	 * @return the head
	 */
	public static Vector<Head> getHeadData() {
		return head;
	}

	/**
	 * @param mHead the head to set
	 */
	public static void setHeadData(Vector<Head> mHead) {
		AssetsHandler.head = mHead;
	}

	/**
	 * @return the helmet
	 */
	public static Vector<Helmet> getHelmetData() {
		return helmet;
	}

	/**
	 * @param mHelmet the helmet to set
	 */
	public static void setHelmetData(Vector<Helmet> mHelmet) {
		AssetsHandler.helmet = mHelmet;
	}

	/**
	 * @return the weapon
	 */
	public static Vector<Weapon> getWeaponData() {
		return weapon;
	}

	/**
	 * @param mWeapon the weapon to set
	 */
	public static void setWeaponData(Vector<Weapon> mWeapon) {
		AssetsHandler.weapon = mWeapon;
	}

	/**
	 * @return the shield
	 */
	public static Vector<Shield> getShieldData() {
		return shield;
	}

	/**
	 * @param mShield the shield to set
	 */
	public static void setShieldData(Vector<Shield> mShield) {
		AssetsHandler.shield = mShield;
	}

	/**
	 * @return the fx
	 */
	public static Vector<Fx> getFxData() {
		return fx;
	}

	/**
	 * @param mFx the fx to set
	 */
	public static void setFxData(Vector<Fx> mFx) {
		AssetsHandler.fx = mFx;
	}


}
