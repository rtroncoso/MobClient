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
//package com.mob.client.api.systems.anim;
//
//import com.badlogic.ashley.core.ComponentMapper;
//import com.badlogic.ashley.core.Entity;
//import com.badlogic.ashley.core.Family;
//import com.badlogic.ashley.systems.IteratingSystem;
//import com.mob.client.api.components.map.MapComponent;
//import com.mob.client.api.components.basic.StateComponent;
//import com.mob.client.textures.BundledAnimation;
//
///**
// * @author Rodrigo
// *
// */
//public class TileAnimationSystem extends IteratingSystem {
//
//	// ===========================================================
//	// Constants
//	// ===========================================================
//
//	// ===========================================================
//	// Fields
//	// ===========================================================
//	private ComponentMapper<MapComponent> mChunkComponent;
//	private ComponentMapper<StateComponent> mStateMapper;
//
//	// ===========================================================
//	// Constructors
//	// ===========================================================
//	public TileAnimationSystem() {
//		super(Family.all(MapComponent.class,
//						StateComponent.class)
//					.get());
//
//		// Obtenemos nuestros Mappers
//		this.mChunkComponent = ComponentMapper.getFor(MapComponent.class);
//		this.mStateMapper = ComponentMapper.getFor(StateComponent.class);
//	}
//
//	// ===========================================================
//	// Methods
//	// ===========================================================
//
//	// ===========================================================
//	// Methods for/from SuperClass/Interfaces
//	// ===========================================================
//	@Override
//	public void processEntity(Entity entity, float deltaTime) {
//
//		// Obtenemos los components necesarios
//		MapComponent chunk = this.mChunkComponent.get(entity);
//		StateComponent state = this.mStateMapper.get(entity);
//
//		// Iteramos todos los tiles del chunk
//		for(int y = 1; y <= MapComponent.CHUNK_TILE_SIZE; y++) {
//			for (int x = 1; x <= MapComponent.CHUNK_TILE_SIZE; x++) {
//				for(int layer = 0; layer < MapComponent.CHUNK_LAYERS; layer++) {
//
//					// Obtenemos la animacion
//					BundledAnimation animation = chunk.getTile(x, y).getAnimation(layer);
//
//					// Si tiene una animacion cambiamos su timer
//					if (animation != null && animation.isAnimated()) {
//						animation.setAnimationTime(state.time);
//					}
//				}
//			}
//		}
//
//		// Actualizamos nuestro DeltaTime interno de la Entity
//		state.time += deltaTime;
//	}
//
//	// ===========================================================
//	// Getter & Setter
//	// ===========================================================
//
//	// ===========================================================
//	// Inner and Anonymous Classes
//	// ===========================================================
//}
