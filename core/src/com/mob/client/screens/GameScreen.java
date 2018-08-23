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

import character.Heading;
import com.artemis.Archetype;
import com.artemis.Entity;
import com.artemis.SuperMapper;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.managers.TagManager;
import com.artemis.managers.UuidEntityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mob.client.Game;
import com.mob.client.artemis.archetypes.AOArchetypes;
import com.mob.client.artemis.systems.anim.AnimationSystem;
import com.mob.client.artemis.systems.camera.CameraFocusSystem;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.artemis.systems.camera.CameraMovementSystem;
import com.mob.client.artemis.systems.interactions.DialogSystem;
import com.mob.client.artemis.systems.map.TiledMapSystem;
import com.mob.client.artemis.systems.physics.*;
import com.mob.client.artemis.systems.render.*;

import static com.artemis.E.E;


/**
 * Class GameScreen
 * @author Rodrigo
 */
public class GameScreen extends Screen {

    private Stage stage;
    private Table table;

    public GameScreen(Game game) {
		super(game);
	}

    @Override
    protected void postWorldInit() {
        // register camera?
        Entity cameraEntity = world.createEntity();
        E(cameraEntity)
                .aOCamera(true)
                .pos2D();
        world.getSystem(TagManager.class).register("camera", cameraEntity);
        Archetype playerArchetype = AOArchetypes.player(world);

	    Entity player = world.createEntity(playerArchetype);
        E(player)
                .pos2DX(50)
                .pos2DY(50)
                .worldPosX(50)
                .worldPosY(50)
                .focused(true)
                .playerControllable(true)
                .headingCurrent(Heading.HEADING_NORTH)
                .headIndex(1)
                .bodyIndex(1)
				.statusHealth(100)
                .statusMaxHealth(120)
                .statusHungry(100)
                .statusMana(1000)
                .statusMaxMana(1000)
                .statusStamina(100)
                .statusThirst(100)
                .infoName("guidota2")
                .dialogText("Este es el dialogo papa")
                .dialogAlpha(1)
                .dialogTime(7)
                .canWrite()
                .aOPhysics();

        Entity player2 = world.createEntity(playerArchetype);
        E(player2)
                .pos2DX(50)
                .pos2DY(50)
                .worldPosX(50)
                .worldPosY(50)
                .headingCurrent(Heading.HEADING_NORTH)
                .headIndex(2)
                .bodyIndex(2)
                .dialogText("aa")
                .dialogAlpha(1)
                .dialogTime(7)
                .randomMovement(true)
                .infoName("guidota2")
                .aOPhysics();
    }

    @Override
    protected void initSystems(WorldConfigurationBuilder builder) {
        // WORLD SYSTEMS
        builder
                .with(new SuperMapper())
                // Player movement
                .with(new PlayerInputSystem())
                .with(new MovementSystem())
                .with(new AnimationSystem())
                // Camera
                .with(new CameraSystem(Game.GAME_SCREEN_ZOOM))
                .with(new CameraFocusSystem())
                .with(new CameraMovementSystem())
                // Rendering
                .with(new TiledMapSystem(1))
                .with(WorldConfigurationBuilder.Priority.NORMAL + 3, new MapLowerLayerRenderingSystem(this.game.getSpriteBatch()))
                .with(WorldConfigurationBuilder.Priority.NORMAL + 2, new CharacterRenderingSystem(this.game.getSpriteBatch()))
                .with(WorldConfigurationBuilder.Priority.NORMAL + 1, new MapUpperLayerRenderingSystem(this.game.getSpriteBatch()))
                .with(new CharacterStatusRenderingSystem(game.getSpriteBatch()))
                .with(new NameRenderingSystem(game.getSpriteBatch()))
                .with(new DialogRenderingSystem(game.getSpriteBatch()))
                // Logic systems
                .with(new DialogSystem(table))
                .with(new RandomMovementSystem())
                .with(new TagManager())
                .with(new UuidEntityManager());
	}

    @Override
	protected void initScene() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);


        Container<Table> tableContainer = new Container<Table>();

        float sw = Gdx.graphics.getWidth();
        float sh = Gdx.graphics.getHeight();

        float cw = sw * 1f;
        float ch = sh * 0.1f;

        tableContainer.setSize(cw, ch);
        tableContainer.setPosition((sw - cw) / 2.0f, sh - ch);
        tableContainer.fillX();

        table = new Table();

        tableContainer.setActor(table);
        stage.addActor(tableContainer);
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
    protected void drawUI() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
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
