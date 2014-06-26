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

import org.ini4j.spi.WinEscapeTool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import java.net.URL;

public class Wini extends Ini
{
    private static final long serialVersionUID = -2781377824232440728L;
    public static final char PATH_SEPARATOR = '\\';

    public Wini()
    {
        Config cfg = Config.getGlobal().clone();

        cfg.setEscape(false);
        cfg.setEscapeNewline(false);
        cfg.setGlobalSection(true);
        cfg.setEmptyOption(true);
        cfg.setMultiOption(false);
        cfg.setPathSeparator(PATH_SEPARATOR);
        setConfig(cfg);
    }

    public Wini(File input) throws IOException, InvalidFileFormatException
    {
        this();
        setFile(input);
        load();
    }

    public Wini(URL input) throws IOException, InvalidFileFormatException
    {
        this();
        load(input);
    }

    public Wini(InputStream input) throws IOException, InvalidFileFormatException
    {
        this();
        load(input);
    }

    public Wini(Reader input) throws IOException, InvalidFileFormatException
    {
        this();
        load(input);
    }

    public String escape(String value)
    {
        return WinEscapeTool.getInstance().escape(value);
    }

    public String unescape(String value)
    {
        return WinEscapeTool.getInstance().unescape(value);
    }
}
