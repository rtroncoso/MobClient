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

import org.ini4j.Registry.Key;

class BasicRegistryKey extends BasicProfileSection implements Registry.Key
{
    private static final long serialVersionUID = -1390060044244350928L;
    private static final String META_TYPE = "type";

    public BasicRegistryKey(BasicRegistry registry, String name)
    {
        super(registry, name);
    }

    @Override public Key getChild(String key)
    {
        return (Key) super.getChild(key);
    }

    @Override public Key getParent()
    {
        return (Key) super.getParent();
    }

    @Override public Registry.Type getType(Object key)
    {
        return (Registry.Type) getMeta(META_TYPE, key);
    }

    @Override public Registry.Type getType(Object key, Registry.Type defaultType)
    {
        Registry.Type type = getType(key);

        return (type == null) ? defaultType : type;
    }

    @Override public Key addChild(String key)
    {
        return (Key) super.addChild(key);
    }

    @Override public Key lookup(String... path)
    {
        return (Key) super.lookup(path);
    }

    @Override public Registry.Type putType(String key, Registry.Type type)
    {
        return (Registry.Type) putMeta(META_TYPE, key, type);
    }

    @Override public Registry.Type removeType(Object key)
    {
        return (Registry.Type) removeMeta(META_TYPE, key);
    }
}
