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
package com.mob.client.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mob.client.components.character.BodyComponent;
import com.mob.client.components.character.HeadingComponent;
import com.mob.client.components.basic.StateComponent;
import com.mob.client.textures.BundledAnimation;

/**
 * @author Rodrigo
 *
 */
public class CharacterAnimationSystem extends IteratingSystem {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private ComponentMapper<BodyComponent> mBodyMapper;
	private ComponentMapper<StateComponent> mStateMapper;
	private ComponentMapper<HeadingComponent> mHeadingMapper;

	// ===========================================================
	// Constructors
	// ===========================================================
	@SuppressWarnings("unchecked")
	public CharacterAnimationSystem() {
		super(Family.all(BodyComponent.class,
						StateComponent.class,
						HeadingComponent.class)
					.get());
		
		// Obtenemos nuestros Mappers
		this.mBodyMapper = ComponentMapper.getFor(BodyComponent.class);
		this.mStateMapper = ComponentMapper.getFor(StateComponent.class);
		this.mHeadingMapper = ComponentMapper.getFor(HeadingComponent.class);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void processEntity(Entity entity, float deltaTime) {
		
		// Obtenemos los components necesarios
		BodyComponent body = this.mBodyMapper.get(entity);
		StateComponent state = this.mStateMapper.get(entity);
		HeadingComponent heading = this.mHeadingMapper.get(entity);
		BundledAnimation animation = body.animations.get(heading.current);
		
		// Si tiene una animación cambiamos la region
		if (animation != null) {
			animation.setAnimationTime(state.time);
		}
		
		// Actualizamos nuestro DeltaTime interno de la Entity
		state.time += deltaTime;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
