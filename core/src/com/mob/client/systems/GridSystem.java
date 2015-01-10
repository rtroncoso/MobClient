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
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.mob.client.components.LineComponent;
import com.mob.client.handlers.CameraHandler;

/**
 * @author Rodrigo
 *
 */
public class GridSystem extends IteratingSystem {

	// ===========================================================
	// Constants
	// ===========================================================
	@SuppressWarnings("unchecked")
	private static final Family family = Family.one(LineComponent.class).get();

	// ===========================================================
	// Fields
	// ===========================================================
	private ComponentMapper<LineComponent> mLineMapper;
	
	private ShapeRenderer mShapeRenderer = new ShapeRenderer();
	private Array<Entity> mRenderQueue;

	// ===========================================================
	// Constructors
	// ===========================================================
	public GridSystem() {
		super(family);

		// Obtenemos nuestros Mappers
		this.mLineMapper = ComponentMapper.getFor(LineComponent.class);
		
		// Necesario para el ShapeRenderer
		this.mShapeRenderer.setAutoShapeType(true);
		
		// Creamos la render queue
		this.mRenderQueue = new Array<Entity>();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		
		// Agregamos la entidad a la queue
		this.mRenderQueue.add(entity);

	}
	
	/**
	 * Este metodo se encarga de renderizar las lineas de nuestro
	 * GridSystem
	 * 
	 * @author rt
	 * @param deltaTime
	 */
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		// Obtenemos una entity de la queue y inicializamos el batch
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		CameraHandler.getCamera().update();
		this.mShapeRenderer.setProjectionMatrix(CameraHandler.getCamera().combined);
		this.mShapeRenderer.begin();
		
		// Iteramos todos los characters
		for (Entity entity : this.mRenderQueue) {
			
			// Obtenemos los components del character
			LineComponent line = this.mLineMapper.get(entity);
			
			// Dibujamos la linea actual
			this.mShapeRenderer.setColor(new Color(0, 255, 0, 0.5f));
			this.mShapeRenderer.line(line.start.x, line.start.y, line.end.x, line.end.y);
		}
		
		// Finalizamos el ShapeRenderer y limpiamos la queue
		this.mShapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
		this.mRenderQueue.clear();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
