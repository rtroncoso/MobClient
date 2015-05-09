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
package com.mob.dao.loaders;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

import com.mob.dao.objects.Fx;
import com.mob.client.util.Util;

public class FxLoader extends Loader<Vector<Fx>> {

	@Override
	public Vector<Fx> load(DataInputStream file) throws IOException {
		Vector<Fx> fxs = new Vector<Fx>();
		int numFxs;

		file.skipBytes(GAME_FILE_HEADER_SIZE);
        numFxs = Util.leShort(file.readShort());
        fxs.setSize(numFxs + 1);

		for(int i = 1; i <= numFxs; i++) {
			int offsetX, offsetY, fxIndex;

			fxIndex = Util.leShort(file.readShort());
			offsetX = Util.leShort(file.readShort());
			offsetY = Util.leShort(file.readShort());

			fxs.setElementAt(new Fx(fxIndex, offsetX, offsetY), i);
		}

		return fxs;
	}

}
