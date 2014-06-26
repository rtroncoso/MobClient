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
package com.mob.client.input;

import com.badlogic.gdx.InputProcessor;

public class GeneralInputProcessor implements InputProcessor {
   @Override
   public boolean keyDown (int keycode) {
      return false;
   }

   @Override
   public boolean keyUp (int keycode) {
      return false;
   }

   @Override
   public boolean keyTyped (char character) {
      return false;
   }

   @Override
   public boolean touchDown (int x, int y, int pointer, int button) {
      return false;
   }

   @Override
   public boolean touchUp (int x, int y, int pointer, int button) {
      return false;
   }

   @Override
   public boolean touchDragged (int x, int y, int pointer) {
      return false;
   }

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

   @Override
   public boolean scrolled (int amount) {
      return false;
   }
}