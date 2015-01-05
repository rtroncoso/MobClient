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

import com.badlogic.gdx.graphics.Color;
import com.mob.client.components.CharacterComponent;
import com.mob.client.components.ColorComponent;
import com.mob.client.components.HeadingComponent;
import com.mob.client.components.MovementComponent;
import com.mob.client.components.StateComponent;
import com.mob.client.components.TransformComponent;
import com.mob.client.data.BodyData;
import com.mob.client.data.HeadData;
import com.mob.client.entities.Character;
import com.mob.client.handlers.AssetsHandler;

/**
 * @author Rodrigo
 *
 */
public class CharacterFactory extends Factory<Character> {

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
	/**
	 * Chaining method que nos permite agregar un body a nuestro scope
	 * 
	 * @param body
	 * @return CharacterFactory
	 */
	public CharacterFactory withBody(BodyData body) {
		
		// Setteamos el body en la entity
		this.mScope.setBody(body);
		
		// Devolvemos nuestra instancia para chaining
		return this;
	}
	
	/**
	 * Chaining method que nos permite agregar una head a nuestro scope
	 * 
	 * @param head
	 * @return CharacterFactory
	 */
	public CharacterFactory withHead(HeadData head) {
		
		// Setteamos la head en la entity
		this.mScope.setHead(head);
		
		// Devolvemos nuestra instancia para chaining
		return this;
	}
	
	/**
	 * Chaining method que nos permite agregar un movement component a nuestro scope
	 * 
	 * @param movement
	 * @return CharacterFactory
	 */
	public CharacterFactory withMovement(MovementComponent movement) {
		
		// Setteamos el movement component en la entity
		this.mScope.setMovementComponent(movement);
		
		// Devolvemos nuestra instancia para chaining
		return this;
	}
	
	/**
	 * Chaining method que nos permite agregar un color component a nuestro scope
	 * 
	 * @param color
	 * @return CharacterFactory
	 */
	public CharacterFactory withColor(ColorComponent color) {
		
		// Setteamos el color component en la entity
		this.mScope.setColorComponent(color);
		
		// Devolvemos nuestra instancia para chaining
		return this;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public CharacterFactory create() {
		
		// Creamos la entity a guardar
		this.mScope = new Character();
		
		// Declaramos los components a usar
		CharacterComponent character = new CharacterComponent();
		ColorComponent color = new ColorComponent();
		MovementComponent movement = new MovementComponent();
		StateComponent state = new StateComponent();
		TransformComponent transform = new TransformComponent();
		HeadingComponent heading = new HeadingComponent();
		
		// Initial states de un character
		transform.pos.set(500.0f, 300.0f, 3.0f);
		color.tint = Color.WHITE;
		heading.current = HeadingComponent.HEADING_EAST;
		
		// Default components
		this.withBody(AssetsHandler.getBody(1))
			.withHead(AssetsHandler.getHead(2))
			.withMovement(movement)
			.withColor(color);
		
		// Agregamos los components necesarios a la entity
		this.mScope.add(character);
		this.mScope.add(heading);
		this.mScope.add(color);
		this.mScope.add(movement);
		this.mScope.add(state);
		this.mScope.add(transform);
		
		// Devolvemos nuestra instancia para chainear
		return this;
	}

	/**
	 * Devuelve nuestro character scope listo para enviar a un engine
	 * 
	 * @return Character
	 */
	@Override
	public Character get() {
		return this.mScope;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
