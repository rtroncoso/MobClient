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
import com.badlogic.gdx.math.Vector2;
import com.mob.client.components.MovementComponent;
import com.mob.client.components.TransformComponent;

/**
 * @author Rodrigo
 *
 */
public class MovementSystem extends IteratingSystem {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private Vector2 tmp = new Vector2();

	private ComponentMapper<TransformComponent> mTransformMapper;
	private ComponentMapper<MovementComponent> mMovementMapper;

	// ===========================================================
	// Constructors
	// ===========================================================
	public MovementSystem() {
		super(Family.getFor(MovementComponent.class, TransformComponent.class));
		
		this.mTransformMapper = ComponentMapper.getFor(TransformComponent.class);
		this.mMovementMapper = ComponentMapper.getFor(MovementComponent.class);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		TransformComponent pos = this.mTransformMapper.get(entity);
		MovementComponent mov = this.mMovementMapper.get(entity);;
		
		tmp.set(mov.accel).scl(deltaTime);
		mov.velocity.add(tmp);
		
		tmp.set(mov.velocity).scl(deltaTime);
		pos.pos.add(tmp.x, tmp.y, 0.0f);

	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
