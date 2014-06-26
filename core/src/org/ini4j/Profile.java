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

public interface Profile extends MultiMap<String, Profile.Section>, CommentedMap<String, Profile.Section>
{
    char PATH_SEPARATOR = '/';

    String getComment();

    void setComment(String value);

    Section add(String sectionName);

    void add(String sectionName, String optionName, Object value);

    <T> T as(Class<T> clazz);

    <T> T as(Class<T> clazz, String prefix);

    String fetch(Object sectionName, Object optionName);

    <T> T fetch(Object sectionName, Object optionName, Class<T> clazz);

    String get(Object sectionName, Object optionName);

    <T> T get(Object sectionName, Object optionName, Class<T> clazz);

    String put(String sectionName, String optionName, Object value);

    Section remove(Profile.Section section);

    String remove(Object sectionName, Object optionName);

    interface Section extends OptionMap
    {
        Section getChild(String key);

        String getName();

        Section getParent();

        String getSimpleName();

        Section addChild(String key);

        String[] childrenNames();

        Section lookup(String... path);

        void removeChild(String key);
    }
}
