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

import com.badlogic.gdx.math.MathUtils;
import com.mob.client.Game;
import com.mob.client.artemis.manager.EntityFactorySystem;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.artemis.systems.camera.EntityCameraSystem;
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
		this.worldConfiguration.setSystem(new EntityFactorySystem());

        this.worldConfiguration.setSystem(new CameraSystem(1));
        this.worldConfiguration.setSystem(new TiledMapSystem(1));
		this.worldConfiguration.setSystem(new MapRenderingSystem(this.game.getSpriteBatch()));

        this.worldConfiguration.setSystem(new EntityCameraSystem());
	}

    @Override
	protected void initScene() {
        //
	}

    @Override
	protected void update(float deltaTime) {
        this.logger.log();

		world.setDelta(MathUtils.clamp(deltaTime, 0, 1 / 15f));
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
