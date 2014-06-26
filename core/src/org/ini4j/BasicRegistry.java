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

import org.ini4j.spi.IniHandler;
import org.ini4j.spi.RegEscapeTool;
import org.ini4j.spi.TypeValuesPair;

public class BasicRegistry extends BasicProfile implements Registry
{
    private static final long serialVersionUID = -6432826330714504802L;
    private String _version;

    public BasicRegistry()
    {
        _version = VERSION;
    }

    @Override public String getVersion()
    {
        return _version;
    }

    @Override public void setVersion(String value)
    {
        _version = value;
    }

    @Override public Key add(String name)
    {
        return (Key) super.add(name);
    }

    @Override public Key get(Object key)
    {
        return (Key) super.get(key);
    }

    @Override public Key get(Object key, int index)
    {
        return (Key) super.get(key, index);
    }

    @Override public Key put(String key, Section value)
    {
        return (Key) super.put(key, value);
    }

    @Override public Key put(String key, Section value, int index)
    {
        return (Key) super.put(key, value, index);
    }

    @Override public Key remove(Section section)
    {
        return (Key) super.remove(section);
    }

    @Override public Key remove(Object key)
    {
        return (Key) super.remove(key);
    }

    @Override public Key remove(Object key, int index)
    {
        return (Key) super.remove(key, index);
    }

    @Override Key newSection(String name)
    {
        return new BasicRegistryKey(this, name);
    }

    @Override void store(IniHandler formatter, Section section, String option)
    {
        store(formatter, section.getComment(option));
        Type type = ((Key) section).getType(option, Type.REG_SZ);
        String rawName = option.equals(Key.DEFAULT_NAME) ? option : RegEscapeTool.getInstance().quote(option);
        String[] values = new String[section.length(option)];

        for (int i = 0; i < values.length; i++)
        {
            values[i] = section.get(option, i);
        }

        String rawValue = RegEscapeTool.getInstance().encode(new TypeValuesPair(type, values));

        formatter.handleOption(rawName, rawValue);
    }
}
