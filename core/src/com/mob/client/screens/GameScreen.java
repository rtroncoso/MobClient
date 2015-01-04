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

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mob.client.TestGame;
import com.mob.client.factories.CharacterFactory;
import com.mob.client.handlers.AssetsHandler;
import com.mob.client.systems.CharacterAnimationSystem;
import com.mob.client.systems.CharacterRenderingSystem;
import com.mob.client.systems.MovementSystem;

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
	private Engine mEngine;
	private int mState;
	private OrthographicCamera mCamera;
	private CharacterFactory mCharacterFactory;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	public GameScreen(TestGame game) {
		
		// Inicializamos todo lo necesario
		this.mCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.mCamera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		this.mCharacterFactory = new CharacterFactory();
		this.mGame = game;
		this.mState = GAME_RUNNING;
		this.mEngine = new Engine();
		
		// Agregamos los sistemas al engine
		this.mEngine.addSystem(new MovementSystem());
		this.mEngine.addSystem(new CharacterAnimationSystem());
		this.mEngine.addSystem(new CharacterRenderingSystem(this.mGame.getSpriteBatch()));
		
		// Creamos un dummy character y lo agregamos al engine
		this.mEngine.addEntity(
			this.mCharacterFactory.create()
				.withBody(AssetsHandler.getBodyData().get(1))
				.get()
		);
		
		
	}

	// ===========================================================
	// Methods
	// ===========================================================
	public void update (float deltaTime) {
		if (deltaTime > 0.1f) deltaTime = 0.1f;

		this.mEngine.update(deltaTime);
		
		switch (this.mState) {
		case GAME_RUNNING:
			updateRunning(deltaTime);
			break;
		case GAME_PAUSED:
			updatePaused();
			break;
		}
	}

	private void updateRunning (float deltaTime) {
		
		ApplicationType appType = Gdx.app.getType();
		
//		this.mEngine.getSystem(BobSystem.class).setAccelX(accelX);
		
//		if (world.score != lastScore) {
//			lastScore = world.score;
//			scoreString = "SCORE: " + lastScore;
//		}
//		if (world.state == World.WORLD_STATE_NEXT_LEVEL) {
//			game.setScreen(new WinScreen(game));
//		}
//		if (world.state == World.WORLD_STATE_GAME_OVER) {
//			state = GAME_OVER;
//			if (lastScore >= Settings.highscores[4])
//				scoreString = "NEW HIGHSCORE: " + lastScore;
//			else
//				scoreString = "SCORE: " + lastScore;
//			pauseSystems();
//			Settings.addScore(lastScore);
//			Settings.save();
//		}
	}

	private void updatePaused () {
		// TODO : Implementar esto
	}
	
	private void pauseSystems() {
		this.mEngine.getSystem(MovementSystem.class).setProcessing(false);
		this.mEngine.getSystem(CharacterAnimationSystem.class).setProcessing(false);
		this.mEngine.getSystem(CharacterRenderingSystem.class).setProcessing(false);
	}
	
	private void resumeSystems() {
		this.mEngine.getSystem(MovementSystem.class).setProcessing(true);
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
//			this.pauseSystems();
		}
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
