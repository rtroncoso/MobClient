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

import com.mob.dao.objects.*;
import com.mob.dao.objects.Body;
import com.mob.dao.readers.*;

public class AssetsHandler {

	private static Vector<Graphic> graphics = new Vector<Graphic>();
	private static Vector<Body> bodies = new Vector<Body>();
	private static Vector<Head> heads = new Vector<Head>();
	private static Vector<Helmet> helmets = new Vector<Helmet>();
	private static Vector<Weapon> weapons = new Vector<Weapon>();
    private static Vector<Shield> shields = new Vector<Shield>();
    private static Vector<Fx> fxs = new Vector<Fx>();
    private static AOAssetsReader reader = new AOAssetsReader();

	public static void load() {

        graphics = reader.loadGraphics();
        bodies = reader.loadBodies();
        weapons = reader.loadWeapons();
        shields = reader.loadShields();
        heads = reader.loadHeads();
        helmets = reader.loadHelmets();
        fxs = reader.loadFxs();
		
	}

	/**
	 * @param index
	 * @return bodies data of a specified index
	 */
	public static Body getBodies(int index) {
		return AssetsHandler.getBodies().get(index);
	}

	/**
	 * @param index
	 * @return heads data of a specified index
	 */
	public static Head getHeads(int index) {
		return AssetsHandler.getHeads().get(index);
	}
	
	/**
	 * @param index
	 * @return grh data of a specified index
	 */
	public static Graphic getGraphic(int index) {
		return AssetsHandler.getGraphics().get(index);
	}
	
	/**
	 * @return the graphics
	 */
	public static Vector<Graphic> getGraphics() {
		return graphics;
	}

	/**
	 * @param graphic the graphics to set
	 */
	public static void setGraphics(Vector<Graphic> graphic) {
		AssetsHandler.graphics = graphic;
	}

	/**
	 * @return the bodies
	 */
	public static Vector<Body> getBodies() {
		return bodies;
	}

	/**
	 * @param body the bodies to set
	 */
	public static void setBodies(Vector<Body> body) {
		AssetsHandler.bodies = body;
	}

	/**
	 * @return the heads
	 */
	public static Vector<Head> getHeads() {
		return heads;
	}

	/**
	 * @param heads the heads to set
	 */
	public static void setHeads(Vector<Head> heads) {
		AssetsHandler.heads = heads;
	}

	/**
	 * @return the helmets
	 */
	public static Vector<Helmet> getHelmets() {
		return helmets;
	}

	/**
	 * @param helmets the helmets to set
	 */
	public static void setHelmets(Vector<Helmet> helmets) {
		AssetsHandler.helmets = helmets;
	}

	/**
	 * @return the weapons
	 */
	public static Vector<Weapon> getWeapons() {
		return weapons;
	}

	/**
	 * @param weapons the weapons to set
	 */
	public static void setWeapons(Vector<Weapon> weapons) {
		AssetsHandler.weapons = weapons;
	}

	/**
	 * @return the shields
	 */
	public static Vector<Shield> getShields() {
		return shields;
	}

	/**
	 * @param shields the shields to set
	 */
	public static void setShields(Vector<Shield> shields) {
		AssetsHandler.shields = shields;
	}

	/**
	 * @return the fxs
	 */
	public static Vector<Fx> getFxs() {
		return fxs;
	}

	/**
	 * @param fxs the fxs to set
	 */
	public static void setFxs(Vector<Fx> fxs) {
		AssetsHandler.fxs = fxs;
	}


}
