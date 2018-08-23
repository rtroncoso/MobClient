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
package position;

import com.artemis.Component;

/**
 * WorldPos Class
 *
 * @author Rodrigo
 * @package com.mob.client.api.components.position
 */
public class WorldPos extends Component {

    public int x;
    public int y;

    public WorldPos() {
        this.x = 0;
        this.y = 0;
    }
    public WorldPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
