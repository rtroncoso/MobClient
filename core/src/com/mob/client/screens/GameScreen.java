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

import com.artemis.*;
import com.artemis.managers.TagManager;
import com.artemis.managers.UuidEntityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mob.client.Game;
import com.mob.client.artemis.systems.anim.MovementAnimationSystem;
import com.mob.client.artemis.systems.camera.CameraFocusSystem;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.artemis.systems.camera.CameraMovementSystem;
import com.mob.client.artemis.systems.interactions.DialogSystem;
import com.mob.client.artemis.systems.interactions.MeditateSystem;
import com.mob.client.artemis.systems.map.TiledMapSystem;
import com.mob.client.artemis.systems.network.ClientSystem;
import com.mob.client.artemis.systems.physics.*;
import com.mob.client.artemis.systems.render.*;
import com.mob.network.login.LoginRequest;
import net.mostlyoriginal.api.network.marshal.kryonet.KryonetClientMarshalStrategy;

import static com.artemis.E.E;


/**
 * Class GameScreen
 * @author Rodrigo
 */
public class GameScreen extends Screen {

    public static final int FONTS_PRIORITY = WorldConfigurationBuilder.Priority.NORMAL + 3;
    private Stage stage;
    private Table dialog;
    private KryonetClientMarshalStrategy client;

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
        client.sendToAll(new LoginRequest("guidota", ""));
    }

    @Override
    protected void initSystems(WorldConfigurationBuilder builder) {
        // WORLD SYSTEMS
        client = new KryonetClientMarshalStrategy("localhost", 7666);
        builder
                .with(new SuperMapper())
                .with(new ClientSystem(client))
                // Player movement
                .with(new PlayerInputSystem())
                .with(new MovementSystem())
                .with(new MovementAnimationSystem())
                // Camera
                .with(new CameraSystem(Game.GAME_SCREEN_ZOOM))
                .with(new CameraFocusSystem())
                .with(new CameraMovementSystem())
                // Rendering
                .with(WorldConfigurationBuilder.Priority.NORMAL + 5, new TiledMapSystem())
                .with(WorldConfigurationBuilder.Priority.NORMAL + 4, new MapLowerLayerRenderingSystem(this.game.getSpriteBatch()))
                .with(WorldConfigurationBuilder.Priority.NORMAL + 2, new CharacterRenderingSystem(this.game.getSpriteBatch()))
                .with(WorldConfigurationBuilder.Priority.NORMAL + 1, new FXsRenderingSystem(this.game.getSpriteBatch()))
                .with(WorldConfigurationBuilder.Priority.NORMAL + 1, new MapUpperLayerRenderingSystem(this.game.getSpriteBatch()))
                .with(new CharacterStatusRenderingSystem(game.getSpriteBatch()))
                .with(FONTS_PRIORITY, new NameRenderingSystem(game.getSpriteBatch()))
                .with(FONTS_PRIORITY, new DialogRenderingSystem(game.getSpriteBatch()))
                .with(FONTS_PRIORITY, new CharacterStatesRenderingSystem(game.getSpriteBatch()))
                // Logic systems
                .with(new MeditateSystem())
                .with(new DialogSystem(dialog))
                .with(new RandomMovementSystem())
                .with(new TagManager())
                .with(new UuidEntityManager());
	}

    @Override
	protected void initScene() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Container<Table> dialogContainer = createDialogContainer();
        stage.addActor(dialogContainer);
    }

    public static World getWorld() {
        return world;
    }

    private Container<Table> createDialogContainer() {
        Container<Table> dialogContainer = new Container<Table>();

        float screenW = Gdx.graphics.getWidth();
        float screenH = Gdx.graphics.getHeight();
        float containerW = screenW * 0.8f;
        dialogContainer.setWidth(containerW);
        dialogContainer.setPosition((screenW - containerW) / 2.0f, screenH * 0.25f);
        dialogContainer.fillX();
        dialog = new Table();
        dialogContainer.setActor(dialog);
        return dialogContainer;
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
