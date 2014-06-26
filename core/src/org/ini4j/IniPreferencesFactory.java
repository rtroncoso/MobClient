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

import java.io.InputStream;

import java.net.URI;
import java.net.URL;

import java.util.Properties;
import java.util.prefs.Preferences;
import java.util.prefs.PreferencesFactory;

public class IniPreferencesFactory implements PreferencesFactory
{
    public static final String PROPERTIES = "ini4j.properties";
    public static final String KEY_USER = "org.ini4j.prefs.user";
    public static final String KEY_SYSTEM = "org.ini4j.prefs.system";
    private Preferences _system;
    private Preferences _user;

    @Override public synchronized Preferences systemRoot()
    {
        if (_system == null)
        {
            _system = newIniPreferences(KEY_SYSTEM);
        }

        return _system;
    }

    @Override public synchronized Preferences userRoot()
    {
        if (_user == null)
        {
            _user = newIniPreferences(KEY_USER);
        }

        return _user;
    }

    String getIniLocation(String key)
    {
        String location = Config.getSystemProperty(key);

        if (location == null)
        {
            try
            {
                Properties props = new Properties();

                props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES));
                location = props.getProperty(key);
            }
            catch (Exception x)
            {
                assert true;
            }
        }

        return location;
    }

    URL getResource(String location) throws IllegalArgumentException
    {
        try
        {
            URI uri = new URI(location);
            URL url;

            if (uri.getScheme() == null)
            {
                url = Thread.currentThread().getContextClassLoader().getResource(location);
            }
            else
            {
                url = uri.toURL();
            }

            return url;
        }
        catch (Exception x)
        {
            throw (IllegalArgumentException) new IllegalArgumentException().initCause(x);
        }
    }

    InputStream getResourceAsStream(String location) throws IllegalArgumentException
    {
        try
        {
            return getResource(location).openStream();
        }
        catch (Exception x)
        {
            throw (IllegalArgumentException) new IllegalArgumentException().initCause(x);
        }
    }

    Preferences newIniPreferences(String key)
    {
        Ini ini = new Ini();
        String location = getIniLocation(key);

        if (location != null)
        {
            try
            {
                ini.load(getResourceAsStream(location));
            }
            catch (Exception x)
            {
                throw (IllegalArgumentException) new IllegalArgumentException().initCause(x);
            }
        }

        return new IniPreferences(ini);
    }
}
