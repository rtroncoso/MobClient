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
import com.badlogic.gdx.graphics.Color;
import com.mob.client.components.BodyComponent;
import com.mob.client.components.CharacterComponent;
import com.mob.client.components.ColorComponent;
import com.mob.client.components.HeadingComponent;
import com.mob.client.components.MovementComponent;
import com.mob.client.components.StateComponent;
import com.mob.client.components.TransformComponent;
import com.mob.client.data.BodyData;
import com.mob.client.data.GrhData;
import com.mob.client.handlers.AssetsHandler;
import com.mob.client.textures.BundledAnimation;

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
	public CharacterFactory withBody(BodyData body) {
		
		// Creamos un nuevo BodyComponent
		BodyComponent bodyComponent = new BodyComponent();
		
		// Iteramos nuestros headings
		int index = 0;
		for(int grhIndex : body.getBodyArray()) {
			
			// Obtenemos GrhData necesario
			GrhData grh = AssetsHandler.getGrh(grhIndex);
			
			// Agregamos las animations
			bodyComponent.animations.put(index, new BundledAnimation(grh));
			index++;
		}
		
		// Agregamos el component a nuestra entity
		this.mScope.add(bodyComponent);
		
		return this;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public CharacterFactory create() {
		
		// Creamos la entity a guardar
		Entity entity = new Entity();
		
		// Declaramos los components a usar
		CharacterComponent character = new CharacterComponent();
		ColorComponent color = new ColorComponent();
		MovementComponent movement = new MovementComponent();
		StateComponent state = new StateComponent();
		TransformComponent transform = new TransformComponent();
		HeadingComponent heading = new HeadingComponent();
		
		// Initial states de un character
		transform.pos.set(50.0f, 50.0f, 0.0f);
		color.tint = Color.WHITE;
		heading.current = HeadingComponent.HEADING_SOUTH;
		
		// Agregamos los components necesarios a la entity
		entity.add(character);
		entity.add(heading);
		entity.add(color);
		entity.add(movement);
		entity.add(state);
		entity.add(transform);
		
		// Apuntamos nuestro scope a la entity creada
		this.mScope = entity;
		
		// Devolvemos nuestra instancia para chainear
		return this;
	}

	@Override
	public Entity get() {
		return this.mScope;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
