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
package com.mob.client.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mob.client.MobClient;

public class DesktopLauncher {
	public static void main (String[] arg) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Client";
        cfg.width = MobClient.GAME_SCREEN_WIDTH;
        cfg.height = MobClient.GAME_SCREEN_HEIGHT;
        cfg.fullscreen = MobClient.GAME_FULL_SCREEN;
        cfg.vSyncEnabled = false;
        cfg.foregroundFPS = 0;
        cfg.resizable = false;
        
        new LwjglApplication(new MobClient(), cfg);
	}
}