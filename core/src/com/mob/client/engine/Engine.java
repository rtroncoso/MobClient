///*******************************************************************************
// * Copyright (C) 2014  Rodrigo Troncoso
// *
// *     This program is free software: you can redistribute it and/or modify
// *     it under the terms of the GNU Affero General Public License as
// *     published by the Free Software Foundation, either version 3 of the
// *     License, or (at your option) any later version.
// *
// *     This program is distributed in the hope that it will be useful,
// *     but WITHOUT ANY WARRANTY; without even the implied warranty of
// *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// *     GNU Affero General Public License for more details.
// *
// *     You should have received a copy of the GNU Affero General Public License
// *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
// *******************************************************************************/
///**
// * Draws a complete map into the screen
// * @author Rodrigo Troncoso
// * @version 0.1
// * @since 2014-04-10
// */
//package com.mob.client.engine;
//
//import com.badlogic.gdx.graphics.Color;
//import com.mob.client.Game;
//import com.mob.client.elements.Character;
//import com.mob.client.elements.Tile;
//import com.mob.client.interfaces.Constants;
//
//public abstract class Engine implements Constants {
//
//	// ===========================================================
//	// Constants
//	// ===========================================================
//
//
//	// ===========================================================
//	// Fields
//	// ===========================================================
//	protected Tile[][] tiles;
//	protected float mTechoAB;
//	protected int mMapNumber;
//	protected Color mTint;
//	protected Game game;
//
//	// ===========================================================
//	// Constructors
//	// ===========================================================
//	public Engine(Game pGame) {
//		this.game = pGame;
//		this.mTint = new Color(Color.WHITE);
//		this.mTechoAB = 1.0f;
//		this.mMapNumber = 0;
//	}
//
//	/**
//	 * @param mMapNumber
//	 * @param game
//	 */
//	public Engine(Game pGame, int pMapNumber) {
//
//		this.mMapNumber = pMapNumber;
//		this.game = pGame;
//
//		this.load();
//	}
//
//	// ===========================================================
//	// Methods for/from SuperClass/Interfaces
//	// ===========================================================
//	public abstract void update(float dt);
//	public abstract void reset();
//	public abstract void show();
//	public abstract void draw();
//	public abstract void load();
//	public abstract void dispose();
//
//	// ===========================================================
//	// Getter & Setter
//	// ===========================================================
//	/**
//	 * @return the tiles
//	 */
//	public Tile[][] getTilesArray() {
//		return tiles;
//	}
//
//	/**
//	 * @param tiles the tiles to set
//	 */
//	public void setTilesArray(Tile[][] tiles) {
//		this.tiles = tiles;
//	}
//
//	/**
//	 * @return the mMapNumber
//	 */
//	public int getMapNumber() {
//		return mMapNumber;
//	}
//	/**
//	 * @param mMapNumber the mMapNumber to set
//	 */
//	public void setMap(int mMapNumber) {
//		this.mMapNumber = mMapNumber;
//		this.load();
//	}
//
//	/**
//	 * @return the game
//	 */
//	public Game getGame() {
//		return game;
//	}
//	/**
//	 * @param game the game to set
//	 */
//	public void setGame(Game game) {
//		this.game = game;
//	}
//
//	/**
//	 * @return the mTint
//	 */
//	public Color getTint() {
//		return mTint;
//	}
//
//	/**
//	 * @param mTint the mTint to set
//	 */
//	public void setTint(Color mTint) {
//		this.mTint = mTint;
//	}
//
//	public Tile getTile(int pX, int pY) {
//		return this.tiles[pX][pY];
//	}
//
//	public void setTile(int pX, int pY, Tile pMapBlock) {
//		this.tiles[pX][pY] = pMapBlock;
//	}
//
//	public Character getCharacter(int pX, int pY) {
//		return this.tiles[pX][pY].getCharacter();
//	}
//
//	public void setCharacter(int pX, int pY, Character pCharacter) {
//		this.tiles[pX][pY].setCharIndex(pCharacter.getCharIndex());
//		this.tiles[pX][pY].setCharacter(pCharacter);
//	}
//
//	// ===========================================================
//	// Methods
//	// ===========================================================
//
//	// ===========================================================
//	// Inner and Anonymous Classes
//	// ===========================================================
//
//}
