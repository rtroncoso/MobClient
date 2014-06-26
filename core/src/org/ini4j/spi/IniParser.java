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

import java.util.Locale;

public class IniParser extends AbstractParser
{
    private static final String COMMENTS = ";#'";
    private static final String OPERATORS = ":=";
    static final char SECTION_BEGIN = '[';
    static final char SECTION_END = ']';

    public IniParser()
    {
        super(OPERATORS, COMMENTS);
    }

    public static IniParser newInstance()
    {
        return ServiceFinder.findService(IniParser.class);
    }

    public static IniParser newInstance(Config config)
    {
        IniParser instance = newInstance();

        instance.setConfig(config);

        return instance;
    }

    public void parse(InputStream input, IniHandler handler) throws IOException, InvalidFileFormatException
    {
        parse(newIniSource(input, handler), handler);
    }

    public void parse(Reader input, IniHandler handler) throws IOException, InvalidFileFormatException
    {
        parse(newIniSource(input, handler), handler);
    }

    public void parse(URL input, IniHandler handler) throws IOException, InvalidFileFormatException
    {
        parse(newIniSource(input, handler), handler);
    }

    private void parse(IniSource source, IniHandler handler) throws IOException, InvalidFileFormatException
    {
        handler.startIni();
        String sectionName = null;

        for (String line = source.readLine(); line != null; line = source.readLine())
        {
            if (line.charAt(0) == SECTION_BEGIN)
            {
                if (sectionName != null)
                {
                    handler.endSection();
                }

                sectionName = parseSectionLine(line, source, handler);
            }
            else
            {
                if (sectionName == null)
                {
                    if (getConfig().isGlobalSection())
                    {
                        sectionName = getConfig().getGlobalSectionName();
                        handler.startSection(sectionName);
                    }
                    else
                    {
                        parseError(line, source.getLineNumber());
                    }
                }

                parseOptionLine(line, handler, source.getLineNumber());
            }
        }

        if (sectionName != null)
        {
            handler.endSection();
        }

        handler.endIni();
    }

    private String parseSectionLine(String line, IniSource source, IniHandler handler) throws InvalidFileFormatException
    {
        String sectionName;

        if (line.charAt(line.length() - 1) != SECTION_END)
        {
            parseError(line, source.getLineNumber());
        }

        sectionName = unescapeFilter(line.substring(1, line.length() - 1).trim());
        if ((sectionName.length() == 0) && !getConfig().isUnnamedSection())
        {
            parseError(line, source.getLineNumber());
        }

        if (getConfig().isLowerCaseSection())
        {
            sectionName = sectionName.toLowerCase(Locale.getDefault());
        }

        handler.startSection(sectionName);

        return sectionName;
    }
}
