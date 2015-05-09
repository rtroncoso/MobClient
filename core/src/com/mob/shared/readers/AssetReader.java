/**
 * ****************************************************************************
 * Copyright (C) 2014  Rodrigo Troncoso
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * *****************************************************************************
 */
package com.mob.shared.readers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mob.shared.loaders.Loader;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Class AssetReader
 *
 * Reads data from a Gdx {@link FileHandle} and returns
 * it as a Java {@link DataInputStream}
 */
public class AssetReader<T> {

    protected Loader<T> loader;

    protected String path;

    public T read() {
        FileHandle fileHandle = Gdx.files.internal(path);

        try {
            T loadedFile = loader.load(new DataInputStream(fileHandle.read()));

            Gdx.app.log(this.getClass().getSimpleName(), "[AssetReader] Asset " + path + " successfully loaded");
            return loadedFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
