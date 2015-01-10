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

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ScreenAdapter;
import com.mob.client.TestGame;
import com.mob.client.entities.Character;
import com.mob.client.entities.Chunk;
import com.mob.client.factories.CharacterFactory;
import com.mob.client.factories.ChunkFactory;
import com.mob.client.factories.GridFactory;
import com.mob.client.handlers.CharacterHandler;
import com.mob.client.handlers.MapHandler;
import com.mob.client.systems.*;

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
	private PooledEngine mEngine;
	private int mState;
	
	private CharacterFactory mCharacterFactory = new CharacterFactory();
	private Character mDummyCharacter;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public GameScreen(TestGame game) {
		
		// Inicializamos todo lo necesario
		this.mGame = game;
		this.mState = GAME_RUNNING;
		this.mEngine = new PooledEngine();
		this.initSystems();
		this.initScene();
	}

	// ===========================================================
	// Methods
	// ===========================================================
	private void initSystems() {

		// Agregamos los sistemas al engine
		this.mEngine.addSystem(new MovementSystem());
		this.mEngine.addSystem(new CharacterSystem());
		this.mEngine.addSystem(new CharacterAnimationSystem());
		this.mEngine.addSystem(new CharacterRenderingSystem(this.mGame.getSpriteBatch()));
		this.mEngine.addSystem(new ChunkRenderingSystem(this.mGame.getSpriteBatch()));
		this.mEngine.addSystem(new GridSystem());
	}

	private void initScene() {

		// Creamos un mapa y agregamos los chunks al engine
		for(Chunk chunk : ChunkFactory.create(MapHandler.get(1))) {
			this.mEngine.addEntity(chunk);
		}

		// Creamos un dummy character y lo agregamos a nuestro engine y handler
		this.mDummyCharacter = this.mCharacterFactory.create().get();
		this.mEngine.addEntity(this.mDummyCharacter);
		CharacterHandler.add(this.mDummyCharacter);

		// Creamos lineas para el GridSystem
		for(Entity line : GridFactory.create()) {
			this.mEngine.addEntity(line);
		}
	}

	/**
	 * Game Main Loop
	 * 
	 * @param deltaTime
	 */
	public void update (float deltaTime) {
		if (deltaTime > 0.1f) deltaTime = 0.1f;
		
		this.mDummyCharacter.setVelocity(50.0f, 0.0f);

		this.mEngine.update(deltaTime);
		
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
	 * Loop llamado s�lo cuando el juego est� corriendo
	 * 
	 * @param deltaTime
	 */
	private void updateRunning (float deltaTime) {

	}

	/**
	 * Loop llamado solo cuando el juego est� en pausa
	 */
	private void updatePaused () {

	}
	
	private void pauseSystems() {
		
		this.mEngine.getSystem(MovementSystem.class).setProcessing(false);
		this.mEngine.getSystem(CharacterSystem.class).setProcessing(false);
		this.mEngine.getSystem(CharacterAnimationSystem.class).setProcessing(false);
		this.mEngine.getSystem(CharacterRenderingSystem.class).setProcessing(false);
	}
	
	private void resumeSystems() {
		
		this.mEngine.getSystem(MovementSystem.class).setProcessing(true);
		this.mEngine.getSystem(CharacterSystem.class).setProcessing(true);
		this.mEngine.getSystem(CharacterAnimationSystem.class).setProcessing(true);
		this.mEngine.getSystem(CharacterRenderingSystem.class).setProcessing(true);
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
