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

public interface OptionMap extends MultiMap<String, String>, CommentedMap<String, String>
{
    <T> T getAll(Object key, Class<T> clazz);

    void add(String key, Object value);

    void add(String key, Object value, int index);

    <T> T as(Class<T> clazz);

    <T> T as(Class<T> clazz, String keyPrefix);

    String fetch(Object key);

    String fetch(Object key, String defaultValue);

    String fetch(Object key, int index);

    <T> T fetch(Object key, Class<T> clazz);

    <T> T fetch(Object key, Class<T> clazz, T defaultValue);

    <T> T fetch(Object key, int index, Class<T> clazz);

    <T> T fetchAll(Object key, Class<T> clazz);

    void from(Object bean);

    void from(Object bean, String keyPrefix);

    String get(Object key, String defaultValue);

    <T> T get(Object key, Class<T> clazz);

    <T> T get(Object key, Class<T> clazz, T defaultValue);

    <T> T get(Object key, int index, Class<T> clazz);

    String put(String key, Object value);

    String put(String key, Object value, int index);

    void putAll(String key, Object value);

    void to(Object bean);

    void to(Object bean, String keyPrefix);
}
