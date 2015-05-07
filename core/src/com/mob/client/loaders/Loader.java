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
package com.mob.client.loaders;

import com.badlogic.gdx.files.FileHandle;
import com.mob.client.interfaces.Constants;

public abstract class Loader implements Constants {

    public static final String GAME_INIT_PATH = "data/init/";

	protected FileHandle fileHandle;

	/**
	 * @return the fileHandle
	 */
	public FileHandle getFileHandle() {
		return fileHandle;
	}

	/**
	 * @param fileHandle the fileHandle to set
	 */
	public void setFileHandle(FileHandle fileHandle) {
		this.fileHandle = fileHandle;
	}

}
