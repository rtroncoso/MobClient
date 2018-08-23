/*******************************************************************************
 * Copyright (C) 2015  Rodrigo Troncoso
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

import java.io.Serializable;

/**
 * Pos2D Class
 *
 * @author rt
 * @package com.mob.client.api.components.basic
 */
public class Pos2D extends Component implements Serializable {

    public float x;
    public float y;

    public Pos2D(float pX, float pY) {
        this.x = pX;
        this.y = pY;
    }

    public Pos2D() {
        this.x = 0;
        this.y = 0;
    }

}
