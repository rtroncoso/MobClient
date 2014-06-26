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
import org.ini4j.Options;

public class OptionsBuilder implements OptionsHandler
{
    private boolean _header;
    private String _lastComment;
    private Options _options;

    public static OptionsBuilder newInstance(Options opts)
    {
        OptionsBuilder instance = newInstance();

        instance.setOptions(opts);

        return instance;
    }

    public void setOptions(Options value)
    {
        _options = value;
    }

    @Override public void endOptions()
    {

        // comment only .opt file ...
        if ((_lastComment != null) && _header)
        {
            setHeaderComment();
        }
    }

    @Override public void handleComment(String comment)
    {
        if ((_lastComment != null) && _header)
        {
            setHeaderComment();
            _header = false;
        }

        _lastComment = comment;
    }

    @Override public void handleOption(String name, String value)
    {
        if (getConfig().isMultiOption())
        {
            _options.add(name, value);
        }
        else
        {
            _options.put(name, value);
        }

        if (_lastComment != null)
        {
            if (_header)
            {
                setHeaderComment();
            }
            else
            {
                putComment(name);
            }

            _lastComment = null;
        }

        _header = false;
    }

    @Override public void startOptions()
    {
        if (getConfig().isHeaderComment())
        {
            _header = true;
        }
    }

    protected static OptionsBuilder newInstance()
    {
        return ServiceFinder.findService(OptionsBuilder.class);
    }

    private Config getConfig()
    {
        return _options.getConfig();
    }

    private void setHeaderComment()
    {
        if (getConfig().isComment())
        {
            _options.setComment(_lastComment);
        }
    }

    private void putComment(String key)
    {
        if (getConfig().isComment())
        {
            _options.putComment(key, _lastComment);
        }
    }
}
