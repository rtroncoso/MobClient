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
package com.mob.client.screens;

import com.mob.client.Game;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.artemis.systems.map.TiledMapSystem;
import com.mob.client.artemis.systems.render.MapRenderingSystem;

/**
 * Class GameScreen
 * @author Rodrigo
 */
public class GameScreen extends Screen {

	public GameScreen(Game game) {
		super(game);
	}

	@Override
    protected void initSystems() {
        this.world.setSystem(new CameraSystem());
        this.world.setSystem(new TiledMapSystem(1));
		this.world.setSystem(new MapRenderingSystem(this.game.getSpriteBatch()));
	}

    @Override
	protected void initScene() {
        //
	}

    @Override
	protected void update(float deltaTime) {
		if (deltaTime > 0.1f) deltaTime = 0.1f;

        this.logger.log();

		this.world.setDelta(deltaTime);
        this.world.process();
		
		switch (this.state) {
			case GAME_RUNNING: {
				updateRunning(deltaTime);
				break;
			}
			case GAME_PAUSED: {
				updatePaused();
				break;
			}
		}
	}

    @Override
	protected void updateRunning(float deltaTime) {
        //
	}

    @Override
	protected void updatePaused() {
        //
	}

    @Override
	protected void pauseSystems() {
        //
	}

    @Override
	protected void resumeSystems() {
        //
	}



}
