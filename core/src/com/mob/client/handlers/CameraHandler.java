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
package com.mob.client.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Accessor estático a las funciones de la cámara
 * 
 * @author Rodrigo
 *
 */
public class CameraHandler {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private static OrthographicCamera mCamera;

	// ===========================================================
	// Constructors
	// ===========================================================

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
	 * @return the mCamera
	 */
	public static OrthographicCamera getCamera() {
		return CameraHandler.mCamera;
	}

	/**
	 * @param mCamera the mCamera to set
	 */
	public static void setCamera(OrthographicCamera mCamera) {
		CameraHandler.mCamera = mCamera;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
