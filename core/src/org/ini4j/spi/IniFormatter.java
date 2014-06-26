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

import java.io.PrintWriter;
import java.io.Writer;

public class IniFormatter extends AbstractFormatter implements IniHandler
{
    public static IniFormatter newInstance(Writer out, Config config)
    {
        IniFormatter instance = newInstance();

        instance.setOutput((out instanceof PrintWriter) ? (PrintWriter) out : new PrintWriter(out));
        instance.setConfig(config);

        return instance;
    }

    @Override public void endIni()
    {
        getOutput().flush();
    }

    @Override public void endSection()
    {
        getOutput().print(getConfig().getLineSeparator());
    }

    @Override public void startIni()
    {
        assert true;
    }

    @Override public void startSection(String sectionName)
    {
        setHeader(false);
        if (!getConfig().isGlobalSection() || !sectionName.equals(getConfig().getGlobalSectionName()))
        {
            getOutput().print(IniParser.SECTION_BEGIN);
            getOutput().print(escapeFilter(sectionName));
            getOutput().print(IniParser.SECTION_END);
            getOutput().print(getConfig().getLineSeparator());
        }
    }

    private static IniFormatter newInstance()
    {
        return ServiceFinder.findService(IniFormatter.class);
    }
}
