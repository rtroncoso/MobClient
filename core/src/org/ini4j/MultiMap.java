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

import java.util.List;
import java.util.Map;

public interface MultiMap<K, V> extends Map<K, V>
{
    List<V> getAll(Object key);

    void add(K key, V value);

    void add(K key, V value, int index);

    V get(Object key, int index);

    int length(Object key);

    V put(K key, V value, int index);

    List<V> putAll(K key, List<V> values);

    V remove(Object key, int index);
}
