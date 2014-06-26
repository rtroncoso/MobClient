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
package org.ini4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import java.net.URL;

public interface Persistable
{
    File getFile();

    void setFile(File value);

    void load() throws IOException, InvalidFileFormatException;

    void load(InputStream input) throws IOException, InvalidFileFormatException;

    void load(Reader input) throws IOException, InvalidFileFormatException;

    void load(File input) throws IOException, InvalidFileFormatException;

    void load(URL input) throws IOException, InvalidFileFormatException;

    void store() throws IOException;

    void store(OutputStream output) throws IOException;

    void store(Writer output) throws IOException;

    void store(File output) throws IOException;
}
