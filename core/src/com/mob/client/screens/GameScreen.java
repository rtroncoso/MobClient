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
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.FPSLogger;
import com.mob.client.TestGame;
import com.mob.client.api.systems.camera.CameraSystem;
import com.mob.client.api.systems.map.TiledMapSystem;
import com.mob.client.api.systems.render.MapRenderingSystem;

/**
 * @author Rodrigo
 *
 */
public class GameScreen extends ScreenAdapter {

	// ===========================================================
	// Constants
	// ===========================================================
	public static final int GAME_RUNNING = 0;
	public static final int GAME_PAUSED = 1;

	// ===========================================================
	// Fields
	// ===========================================================
	private TestGame mGame;
    private World mWorld;
	private int mState;
    private FPSLogger logger;
	
	private Character mDummyCharacter;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public GameScreen(TestGame game) {
		
		// Inicializamos todo lo necesario
		this.mGame = game;
		this.mState = GAME_RUNNING;
		this.mWorld = new World();
		this.initSystems();
		this.initScene();


        this.logger = new FPSLogger();
	}

	// ===========================================================
	// Methods
	// ===========================================================
	private void initSystems() {

		// Agregamos los sistemas al engine
//		this.mWorld.setSystem(new MovementSystem());
//		this.mWorld.setSystem(new CharacterAnimationSystem());
//		this.mWorld.setSystem(new TileAnimationSystem());
        this.mWorld.setSystem(new CameraSystem());
        this.mWorld.setSystem(new TiledMapSystem(1));
		this.mWorld.setSystem(new MapRenderingSystem(this.mGame.getSpriteBatch()));
//		this.mWorld.setSystem(new CharacterRenderingSystem(this.mGame.getSpriteBatch()));
//		this.mWorld.setSystem(new GridSystem());
        this.mWorld.initialize();
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
//        Gdx.app.log(this.getClass().getSimpleName(), String.valueOf(Gdx.graphics.getFramesPerSecond()));
		
		this.mWorld.setDelta(deltaTime);
        this.mWorld.process();
		
		switch (this.mState) {
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

	/**
	 * Loop llamado sólo cuando el juego esté corriendo
	 * 
	 * @param deltaTime
	 */
	private void updateRunning (float deltaTime) {

	}

	/**
	 * Loop llamado solo cuando el juego esté en pausa
	 */
	private void updatePaused () {

	}
	
	private void pauseSystems() {
		
//		this.mWorld.getSystem(MovementSystem.class).setProcessing(false);
//		this.mWorld.getSystem(MapRenderingSystem.class).setProcessing(false);
//		this.mWorld.getSystem(CharacterAnimationSystem.class).setProcessing(false);
//		this.mWorld.getSystem(CharacterRenderingSystem.class).setProcessing(false);
	}
	
	private void resumeSystems() {
		
//		this.mWorld.getSystem(MovementSystem.class).setProcessing(true);
//		this.mWorld.getSystem(MapRenderingSystem.class).setProcessing(true);
//		this.mWorld.getSystem(CharacterAnimationSystem.class).setProcessing(true);
//		this.mWorld.getSystem(CharacterRenderingSystem.class).setProcessing(true);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void render (float delta) {
		this.update(delta);
//		this.drawUI();
	}

	@Override
	public void pause () {
		if (this.mState == GAME_RUNNING) {
			this.mState = GAME_PAUSED;
			this.pauseSystems();
		}
	}
	@Override
	public void resume() {
		if(this.mState == GAME_PAUSED) {
			this.mState = GAME_RUNNING;
			this.resumeSystems();
		}
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
