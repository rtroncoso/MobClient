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

public class WinEscapeTool extends EscapeTool
{
    private static final int ANSI_HEX_DIGITS = 2;
    private static final int ANSI_OCTAL_DIGITS = 3;
    private static final int OCTAL_RADIX = 8;
    private static final WinEscapeTool INSTANCE = new WinEscapeTool();

    public static WinEscapeTool getInstance()
    {
        return INSTANCE;
    }

    @Override void escapeBinary(StringBuilder buff, char c)
    {
        buff.append("\\x");
        buff.append(HEX[(c >>> HEX_DIGIT_3_OFFSET) & HEX_DIGIT_MASK]);
        buff.append(HEX[c & HEX_DIGIT_MASK]);
    }

    @Override int unescapeBinary(StringBuilder buff, char escapeType, String line, int index)
    {
        int ret = index;

        if (escapeType == 'x')
        {
            try
            {
                buff.append((char) Integer.parseInt(line.substring(index, index + ANSI_HEX_DIGITS), HEX_RADIX));
                ret = index + ANSI_HEX_DIGITS;
            }
            catch (Exception x)
            {
                throw new IllegalArgumentException("Malformed \\xHH encoding.", x);
            }
        }
        else if (escapeType == 'o')
        {
            try
            {
                buff.append((char) Integer.parseInt(line.substring(index, index + ANSI_OCTAL_DIGITS), OCTAL_RADIX));
                ret = index + ANSI_OCTAL_DIGITS;
            }
            catch (Exception x)
            {
                throw new IllegalArgumentException("Malformed \\oOO encoding.", x);
            }
        }

        return ret;
    }
}
