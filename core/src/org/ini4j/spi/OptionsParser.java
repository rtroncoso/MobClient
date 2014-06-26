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
package org.ini4j.spi;

import org.ini4j.Config;
import org.ini4j.InvalidFileFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import java.net.URL;

public class OptionsParser extends AbstractParser
{
    private static final String COMMENTS = "!#";
    private static final String OPERATORS = ":=";

    public OptionsParser()
    {
        super(OPERATORS, COMMENTS);
    }

    public static OptionsParser newInstance()
    {
        return ServiceFinder.findService(OptionsParser.class);
    }

    public static OptionsParser newInstance(Config config)
    {
        OptionsParser instance = newInstance();

        instance.setConfig(config);

        return instance;
    }

    public void parse(InputStream input, OptionsHandler handler) throws IOException, InvalidFileFormatException
    {
        parse(newIniSource(input, handler), handler);
    }

    public void parse(Reader input, OptionsHandler handler) throws IOException, InvalidFileFormatException
    {
        parse(newIniSource(input, handler), handler);
    }

    public void parse(URL input, OptionsHandler handler) throws IOException, InvalidFileFormatException
    {
        parse(newIniSource(input, handler), handler);
    }

    private void parse(IniSource source, OptionsHandler handler) throws IOException, InvalidFileFormatException
    {
        handler.startOptions();
        for (String line = source.readLine(); line != null; line = source.readLine())
        {
            parseOptionLine(line, handler, source.getLineNumber());
        }

        handler.endOptions();
    }
}
