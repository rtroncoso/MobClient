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
import org.ini4j.Ini;
import org.ini4j.Profile;

public class IniBuilder extends AbstractProfileBuilder implements IniHandler
{
    private Ini _ini;

    public static IniBuilder newInstance(Ini ini)
    {
        IniBuilder instance = newInstance();

        instance.setIni(ini);

        return instance;
    }

    public void setIni(Ini value)
    {
        _ini = value;
    }

    @Override Config getConfig()
    {
        return _ini.getConfig();
    }

    @Override Profile getProfile()
    {
        return _ini;
    }

    private static IniBuilder newInstance()
    {
        return ServiceFinder.findService(IniBuilder.class);
    }
}
