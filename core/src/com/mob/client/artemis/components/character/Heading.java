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
package com.mob.client.artemis.components.character;


import com.artemis.Component;

import java.io.Serializable;

/**
 * Heading Class
 *
 * @author rt
 */
public class Heading extends Component implements Serializable {

	public static final int HEADING_NORTH = 0;
	public static final int HEADING_EAST = 1;
	public static final int HEADING_SOUTH = 2;
	public static final int HEADING_WEST = 3;

	public int current = HEADING_SOUTH;

    public Heading(int heading) {
        this.current = heading;
    }

}
