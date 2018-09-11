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

import com.artemis.Entity;
import com.artemis.SuperMapper;
import com.artemis.World;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.managers.TagManager;
import com.artemis.managers.UuidEntityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.esotericsoftware.minlog.Log;
import com.mob.client.Game;
import com.mob.client.KryonetClientMarshalStrategy;
import com.mob.client.artemis.systems.anim.MovementAnimationSystem;
import com.mob.client.artemis.systems.camera.CameraFocusSystem;
import com.mob.client.artemis.systems.camera.CameraMovementSystem;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.artemis.systems.interactions.DialogSystem;
import com.mob.client.artemis.systems.interactions.MeditateSystem;
import com.mob.client.artemis.systems.map.TiledMapSystem;
import com.mob.client.artemis.systems.network.ClientSystem;
import com.mob.client.artemis.systems.physics.MovementProcessorSystem;
import com.mob.client.artemis.systems.physics.MovementSystem;
import com.mob.client.artemis.systems.physics.PlayerInputSystem;
import com.mob.client.artemis.systems.render.StateRenderingSystem;
import com.mob.client.artemis.systems.render.*;
import com.mob.server.systems.RandomMovementSystem;

import java.util.HashMap;
import java.util.Map;

import static com.artemis.E.E;

public class GameScreen extends Screen {

    public static final int FONTS_PRIORITY = WorldConfigurationBuilder.Priority.NORMAL + 3;
    private Stage stage;
    private Table dialog;
    public static KryonetClientMarshalStrategy client;
    public static int player;
    public static GameScreen instance;

    public static Map<Integer, Integer> networkedEntities = new HashMap<>();

    public GameScreen(Game game) {
        super(game);
        instance = this;
	}

	public static GameScreen getInstance() {
        return instance;
    }

    public static int getPlayer() {
        return player;
    }

    public static void setPlayer(int player) {
        GameScreen.player = player;
        E(player).character();
    }

    @Override
    protected void postWorldInit() {
        Entity cameraEntity = world.createEntity();
        E(cameraEntity)
                .aOCamera(true)
                .pos2D();
        world.getSystem(TagManager.class).register("camera", cameraEntity);
        Log.info("Send login request");

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
                .with(new MovementProcessorSystem())
                .with(new MovementAnimationSystem())
                .with(new MovementSystem())
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
                .with(new StateRenderingSystem(game.getSpriteBatch()))
                .with(new CharacterStatusRenderingSystem(game.getSpriteBatch()))
                .with(FONTS_PRIORITY, new NameRenderingSystem(game.getSpriteBatch()))
                .with(FONTS_PRIORITY, new DialogRenderingSystem(game.getSpriteBatch()))
                .with(FONTS_PRIORITY, new CoordinatesRenderingSystem(game.getSpriteBatch()))
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

    public static KryonetClientMarshalStrategy getClient() {
        return client;
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

    public static boolean entityExsists(int networkId) {
        return networkedEntities.containsKey(networkId);
    }

    public static int getNetworkedEntity(int networkId) {
        return networkedEntities.get(networkId);
    }

    public static void registerEntity(int networkId, int entityId) {
        networkedEntities.put(networkId, entityId);
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
