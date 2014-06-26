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
import org.ini4j.Profile;
import org.ini4j.Reg;

import org.ini4j.Registry.Key;
import org.ini4j.Registry.Type;

public class RegBuilder extends AbstractProfileBuilder
{
    private Reg _reg;

    public static RegBuilder newInstance(Reg reg)
    {
        RegBuilder instance = newInstance();

        instance.setReg(reg);

        return instance;
    }

    public void setReg(Reg value)
    {
        _reg = value;
    }

    @Override public void handleOption(String rawName, String rawValue)
    {
        String name = (rawName.charAt(0) == EscapeTool.DOUBLE_QUOTE) ? RegEscapeTool.getInstance().unquote(rawName) : rawName;
        TypeValuesPair tv = RegEscapeTool.getInstance().decode(rawValue);

        if (tv.getType() != Type.REG_SZ)
        {
            ((Key) getCurrentSection()).putType(name, tv.getType());
        }

        for (String value : tv.getValues())
        {
            super.handleOption(name, value);
        }
    }

    @Override Config getConfig()
    {
        return _reg.getConfig();
    }

    @Override Profile getProfile()
    {
        return _reg;
    }

    private static RegBuilder newInstance()
    {
        return ServiceFinder.findService(RegBuilder.class);
    }
}
