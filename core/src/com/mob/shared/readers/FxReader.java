/**
 * ****************************************************************************
 * Copyright (C) 2014  Rodrigo Troncoso
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * *****************************************************************************
 */
package com.mob.shared.readers;

import com.mob.client.Game;
import com.mob.shared.data.Fx;
import com.mob.shared.data.Helmet;

import java.util.Vector;

public class FxReader extends AssetReader<Vector<Fx>> {

    public Vector<Fx> read(String fileName) {
        return super.read(Game.GAME_INIT_PATH, fileName + ".ind");
    }

}
