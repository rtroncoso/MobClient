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
package com.mob.client.factories;

import com.badlogic.ashley.core.Entity;
import com.mob.client.components.BodyComponent;
import com.mob.client.components.CharacterComponent;
import com.mob.client.components.ColorComponent;
import com.mob.client.components.MovementComponent;
import com.mob.client.components.StateComponent;
import com.mob.client.components.TransformComponent;
import com.mob.client.data.BodyData;

/**
 * @author Rodrigo
 *
 */
public class CharacterFactory extends Factory<Entity> {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public Factory<Entity> withBody(BodyData body) {
		
		return this;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public Factory<Entity> create() {
		
		// Creamos la entity a guardar
		Entity entity = new Entity();
		
		// Declaramos los components a usar
		BodyComponent body = new BodyComponent();
		CharacterComponent character = new CharacterComponent();
		ColorComponent color = new ColorComponent();
		MovementComponent movement = new MovementComponent();
		StateComponent state = new StateComponent();
		TransformComponent transform = new TransformComponent();
		
		// Agregamos los components necesarios a la entity
		entity.add(body);
		entity.add(character);
		entity.add(color);
		entity.add(movement);
		entity.add(state);
		entity.add(transform);
		
		// Apuntamos nuestro scope a la entity creada
		this.mScopedEntity = entity;
		
		// Devolvemos la entity
		return this;
	}

	@Override
	public Entity get() {
		return this.mScopedEntity;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
