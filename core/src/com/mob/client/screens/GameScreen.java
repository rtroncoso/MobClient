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

import com.artemis.World;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.FPSLogger;
import com.mob.client.TestGame;
import com.mob.client.artemis.systems.camera.CameraSystem;
import com.mob.client.artemis.systems.map.TiledMapSystem;
import com.mob.client.artemis.systems.render.MapRenderingSystem;

/**
 * @author Rodrigo
 *
 */
public class GameScreen extends ScreenAdapter {

	public static final int GAME_RUNNING = 0;
	public static final int GAME_PAUSED = 1;

	private TestGame game;
    private World world;
	private int state;
    private FPSLogger logger;
	private Character dummyCharacter;

	public GameScreen(TestGame game) {
		
		// Inicializamos todo lo necesario
		this.game = game;
		this.state = GAME_RUNNING;
		this.world = new World();
		this.initSystems();
		this.initScene();


        this.logger = new FPSLogger();
	}

	private void initSystems() {

		// Agregamos los sistemas al engine
//		this.world.setSystem(new MovementSystem());
//		this.world.setSystem(new CharacterAnimationSystem());
        this.world.setSystem(new CameraSystem());
        this.world.setSystem(new TiledMapSystem(1));
		this.world.setSystem(new MapRenderingSystem(this.game.getSpriteBatch()));
//        this.world.setSystem(new TileAnimationSystem());
//		this.world.setSystem(new CharacterRenderingSystem(this.game.getSpriteBatch()));
//		this.world.setSystem(new GridSystem());
        this.world.initialize();
	}

	private void initScene() {

	}

	/**
	 * Game Main Loop
	 * 
	 * @param deltaTime
	 */
	public void update (float deltaTime) {
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

	private void updateRunning (float deltaTime) {

	}

	private void updatePaused () {

	}
	
	private void pauseSystems() {
		
//		this.world.getSystem(MovementSystem.class).setProcessing(false);
//		this.world.getSystem(MapRenderingSystem.class).setProcessing(false);
//		this.world.getSystem(CharacterAnimationSystem.class).setProcessing(false);
//		this.world.getSystem(CharacterRenderingSystem.class).setProcessing(false);
	}
	
	private void resumeSystems() {
		
//		this.world.getSystem(MovementSystem.class).setProcessing(true);
//		this.world.getSystem(MapRenderingSystem.class).setProcessing(true);
//		this.world.getSystem(CharacterAnimationSystem.class).setProcessing(true);
//		this.world.getSystem(CharacterRenderingSystem.class).setProcessing(true);
	}

	@Override
	public void render (float delta) {
		this.update(delta);
//		this.drawUI();
	}

	@Override
	public void pause () {
		if (this.state == GAME_RUNNING) {
			this.state = GAME_PAUSED;
			this.pauseSystems();
		}
	}
	@Override
	public void resume() {
		if(this.state == GAME_PAUSED) {
			this.state = GAME_RUNNING;
			this.resumeSystems();
		}
	}

}
