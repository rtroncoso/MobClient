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
 * Binary INIT files parsing
 * TODO :
 * 	- Fix reading of VB6 binarys, not being able to read VB6 longs and turn them into Java integers
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-04-10
 */
package com.mob.client.loaders;

import com.badlogic.gdx.files.FileHandle;
import com.mob.client.interfaces.Constants;

public abstract class Loader implements Constants {
	
	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
	protected FileHandle mFileHandle;

	// ===========================================================
	// Constructors
	// ===========================================================
	

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mFileHandle
	 */
	public FileHandle getFileHandle() {
		return mFileHandle;
	}

	/**
	 * @param mFileHandle the mFileHandle to set
	 */
	public void setFileHandle(FileHandle mFileHandle) {
		this.mFileHandle = mFileHandle;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================


}
