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
//package com.mob.client.handlers;
//
//import com.badlogic.ashley.core.Entity;
//import com.badlogic.gdx.utils.LongMap;
//import com.mob.client.entities.Character;
//
//public class CharacterHandler {
//
//	// ===========================================================
//	// Constants
//	// ===========================================================
//
//
//	// ===========================================================
//	// Fields
//	// ===========================================================
//	private static LongMap<Character> mCharacterMap = new LongMap<Character>();
//
//	// ===========================================================
//	// Constructors
//	// ===========================================================
//
//
//	// ===========================================================
//	// Methods for/from SuperClass/Interfaces
//	// ===========================================================
//
//
//	// ===========================================================
//	// Getter & Setter
//	// ===========================================================
//	/**
//	 * @return the mCharacterMap
//	 */
//	public static LongMap<Character> getCharacterMap() {
//		return CharacterHandler.mCharacterMap;
//	}
//	/**
//	 * @param mCharacterMap the mCharacterMap to set
//	 */
//	public static void setCharacterMap(LongMap<Character> mCharacterMap) {
//		CharacterHandler.mCharacterMap = mCharacterMap;
//	}
//
//	/**
//	 * @param key Index in map
//	 * @param pCharacter character to add
//	 */
//	 public static void add(Character pCharacter) {
//		 if (CharacterHandler.mCharacterMap.containsKey(pCharacter.getId())) {
//			 return;
//		 }
//
//	 	CharacterHandler.mCharacterMap.put(pCharacter.getId(), pCharacter);
//	 }
//
//	/**
//	 * Gets a Character from the map
//	 *
//	 * @param key Index in map
//	 * @return Character inside the map
//	 */
//	 public static Character get(long key) {
//		 return CharacterHandler.mCharacterMap.get(key);
//	 }
//
//	/**
//	 * Gets a Character from the map
//	 *
//	 * @param key Index in map
//	 * @return Character inside the map
//	 */
//	 public static Character get(Entity entity) {
//		 return CharacterHandler.get(entity.getId());
//	 }
//
//	/**
//	 * Gets the application player (always index 1)
//	 * @return the player
//	 */
//	 public static Character getPlayer() {
//		 return get(1);
//	 }
//
//	 /**
//	  * Unloads a Character from the map and memory
//	  * @param Character Character in map
//	  */
//	public static void dispose(Character character) {
//		CharacterHandler.dispose(character.getId());
//	}
//
//	 /**
//	  * Unloads a Character from the map and memory
//	  * @param key Index in map
//	  */
//	public static void dispose(long key) {
//		if (!CharacterHandler.mCharacterMap.containsKey(key)) {
//			return;
//		}
//
//		final Character t = CharacterHandler.mCharacterMap.get(key);
//		t.dispose();
//		CharacterHandler.mCharacterMap.remove(key);
//	}
//
//	/**
//	 * Disposes all objects from this handler
//	 */
//	public static void disposeAll() {
//		for (Character t : CharacterHandler.mCharacterMap.values()) {
//			t.dispose();
//		}
//
//		CharacterHandler.mCharacterMap.clear();
//	 }
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
