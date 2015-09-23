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
package com.mob.client.artemis.components.physics;

import com.artemis.Component;
import java.io.Serializable;

/**
 * Physics Class
 *
 * @author rt
 */
public class Physics extends Component implements Serializable {

    public final static float MAX_VELOCITY = Float.MAX_VALUE;
    public final static float WALKING_VELOCITY = 70.0f;

    public float speedX;
    public float speedY;

    public Physics(float speedX, float speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

}
