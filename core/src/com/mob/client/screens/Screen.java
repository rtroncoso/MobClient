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
 * Common screen class. Inherit for individual screen handling
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.screens;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.mob.client.Game;
import com.mob.client.engine.Engine;

public abstract class Screen  implements com.badlogic.gdx.Screen {

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	protected InputMultiplexer mInputMultiplexer;
	private ShaderProgram mDefaultShader;
	private ShaderProgram mLightShader;
	protected Game mGame;
	protected Engine mMap;

	// ===========================================================
	// Constructors
	// ===========================================================
	public Screen (Game game) {
		this.mGame = game;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	public abstract void createScreen ();
	public abstract void update (float dt);
	public abstract void destroy();
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mDefaultShader
	 */
	public ShaderProgram getDefaultShader() {
		return mDefaultShader;
	}

	/**
	 * @param mDefaultShader the mDefaultShader to set
	 */
	public void setDefaultShader(ShaderProgram mDefaultShader) {
		this.mDefaultShader = mDefaultShader;
	}

	/**
	 * @return the mLightShader
	 */
	public ShaderProgram getLightShader() {
		return mLightShader;
	}

	/**
	 * @param mLightShader the mLightShader to set
	 */
	public void setLightShader(ShaderProgram mLightShader) {
		this.mLightShader = mLightShader;
	}

	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
