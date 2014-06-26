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

import org.ini4j.Registry.Type;

public class TypeValuesPair
{
    private final Type _type;
    private final String[] _values;

    @SuppressWarnings("PMD.ArrayIsStoredDirectly")
    public TypeValuesPair(Type type, String[] values)
    {
        _type = type;
        _values = values;
    }

    public Type getType()
    {
        return _type;
    }

    public String[] getValues()
    {
        return _values;
    }
}
