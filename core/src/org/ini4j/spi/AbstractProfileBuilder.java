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

import org.ini4j.CommentedMap;
import org.ini4j.Config;
import org.ini4j.Ini;
import org.ini4j.Profile;

abstract class AbstractProfileBuilder implements IniHandler
{
    private Profile.Section _currentSection;
    private boolean _header;
    private String _lastComment;

    @Override public void endIni()
    {

        // comment only .ini files....
        if ((_lastComment != null) && _header)
        {
            setHeaderComment();
        }
    }

    @Override public void endSection()
    {
        _currentSection = null;
    }

    @Override public void handleComment(String comment)
    {
        if ((_lastComment != null) && _header)
        {
            _header = false;
            setHeaderComment();
        }

        _lastComment = comment;
    }

    @Override public void handleOption(String name, String value)
    {
        _header = false;
        if (getConfig().isMultiOption())
        {
            _currentSection.add(name, value);
        }
        else
        {
            _currentSection.put(name, value);
        }

        if (_lastComment != null)
        {
            putComment(_currentSection, name);
            _lastComment = null;
        }
    }

    @Override public void startIni()
    {
        if (getConfig().isHeaderComment())
        {
            _header = true;
        }
    }

    @Override public void startSection(String sectionName)
    {
        if (getConfig().isMultiSection())
        {
            _currentSection = getProfile().add(sectionName);
        }
        else
        {
            Ini.Section s = getProfile().get(sectionName);

            _currentSection = (s == null) ? getProfile().add(sectionName) : s;
        }

        if (_lastComment != null)
        {
            if (_header)
            {
                setHeaderComment();
            }
            else
            {
                putComment(getProfile(), sectionName);
            }

            _lastComment = null;
        }

        _header = false;
    }

    abstract Config getConfig();

    abstract Profile getProfile();

    Profile.Section getCurrentSection()
    {
        return _currentSection;
    }

    private void setHeaderComment()
    {
        if (getConfig().isComment())
        {
            getProfile().setComment(_lastComment);
        }
    }

    private void putComment(CommentedMap<String, ?> map, String key)
    {
        if (getConfig().isComment())
        {
            map.putComment(key, _lastComment);
        }
    }
}
