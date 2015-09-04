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
package com.mob.client.artemis.systems.map;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.artemis.systems.render.MapRenderingSystem;
import com.mob.dao.objects.Map;
import com.mob.client.handlers.MapHandler;

/**
 * TiledMapSystem Class
 *
 * @author rt
 * @package com.mob.client.api.systems.map
 */
@Wire
public class TiledMapSystem extends BaseSystem {

    public Map map;
    public long mapNumber;

    public TiledMapSystem(long mapNumber) {
        this.mapNumber = mapNumber;
    }

    @Override
    protected void initialize() {
        changeMap(this.mapNumber);
    }

    @Override
    protected void processSystem() {

    }

    /**
     * Change the currently loaded map
     *
     * @param number
     */
    public void changeMap(long number) {
        this.mapNumber = number;
        this.map = MapHandler.get(number);
        this.map.initialize();
    }

}
