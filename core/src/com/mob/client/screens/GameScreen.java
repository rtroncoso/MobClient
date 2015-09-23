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

import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.managers.UuidEntityManager;
import com.badlogic.gdx.math.MathUtils;
import com.mob.client.Game;
import com.mob.client.artemis.manager.EntityFactorySystem;
import com.mob.client.artemis.systems.camera.CameraFocusSystem;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.artemis.systems.camera.CameraMovementSystem;
import com.mob.client.artemis.systems.map.TiledMapSystem;
import com.mob.client.artemis.systems.physics.MovementSystem;
import com.mob.client.artemis.systems.render.CharacterRenderingSystem;
import com.mob.client.artemis.systems.render.MapRenderingSystem;

import net.mostlyoriginal.api.utils.builder.WorldConfigurationBuilder;

/**
 * Class GameScreen
 * @author Rodrigo
 */
public class GameScreen extends Screen {

	public GameScreen(Game game) {
		super(game);
	}

	@Override
    protected void initSystems(WorldConfigurationBuilder builder) {
        // FACTORY SYSTEMS
		builder.with(new EntityFactorySystem());

        // WORLD SYSTEMS
        builder.with(new CameraSystem(1));
        builder.with(new TiledMapSystem(1));
        builder.with(new MovementSystem());

		// CAMERA SYSTEMS
		builder.with(new CameraFocusSystem());
		builder.with(new CameraMovementSystem());

        // RENDERING SYSTEMS
		builder.with(new MapRenderingSystem(this.game.getSpriteBatch()));
		builder.with(new CharacterRenderingSystem(this.game.getSpriteBatch()));

        // MANAGERS
        builder.with(new TagManager());
        builder.with(new GroupManager());
        builder.with(new UuidEntityManager());
	}

    @Override
	protected void initScene() {
        //
	}

    @Override
	protected void update(float deltaTime) {
        this.logger.log();

		world.setDelta(MathUtils.clamp(deltaTime, 0, 1 / 16f));
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
