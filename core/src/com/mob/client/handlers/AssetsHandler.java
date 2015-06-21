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


import com.badlogic.gdx.utils.LongMap;
import com.mob.dao.objects.*;
import com.mob.dao.objects.Body;
import com.mob.dao.readers.*;

public class AssetsHandler {

	private static LongMap<Graphic> graphics = new LongMap<Graphic>();
	private static LongMap<Body> bodies = new LongMap<Body>();
	private static LongMap<Head> heads = new LongMap<Head>();
	private static LongMap<Helmet> helmets = new LongMap<Helmet>();
	private static LongMap<Weapon> weapons = new LongMap<Weapon>();
    private static LongMap<Shield> shields = new LongMap<Shield>();
    private static LongMap<Fx> fxs = new LongMap<Fx>();
    private static AssetsReader reader = new AOAssetsReader();

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
	public static LongMap<Graphic> getGraphics() {
		return graphics;
	}

	/**
	 * @param graphic the graphics to set
	 */
	public static void setGraphics(LongMap<Graphic> graphic) {
		AssetsHandler.graphics = graphic;
	}

	/**
	 * @return the bodies
	 */
	public static LongMap<Body> getBodies() {
		return bodies;
	}

	/**
	 * @param body the bodies to set
	 */
	public static void setBodies(LongMap<Body> body) {
		AssetsHandler.bodies = body;
	}

	/**
	 * @return the heads
	 */
	public static LongMap<Head> getHeads() {
		return heads;
	}

	/**
	 * @param heads the heads to set
	 */
	public static void setHeads(LongMap<Head> heads) {
		AssetsHandler.heads = heads;
	}

	/**
	 * @return the helmets
	 */
	public static LongMap<Helmet> getHelmets() {
		return helmets;
	}

	/**
	 * @param helmets the helmets to set
	 */
	public static void setHelmets(LongMap<Helmet> helmets) {
		AssetsHandler.helmets = helmets;
	}

	/**
	 * @return the weapons
	 */
	public static LongMap<Weapon> getWeapons() {
		return weapons;
	}

	/**
	 * @param weapons the weapons to set
	 */
	public static void setWeapons(LongMap<Weapon> weapons) {
		AssetsHandler.weapons = weapons;
	}

	/**
	 * @return the shields
	 */
	public static LongMap<Fx> getFxs() {
		return fxs;
	}

	/**
	 * @param fxs the fxs to set
	 */
	public static void setFxs(LongMap<Fx> fxs) {
		AssetsHandler.fxs = fxs;
	}


}
