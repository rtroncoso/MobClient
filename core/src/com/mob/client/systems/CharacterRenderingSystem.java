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

import java.util.Comparator;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mob.client.components.character.BodyComponent;
import com.mob.client.components.character.CharacterComponent;
import com.mob.client.components.basic.ColorComponent;
import com.mob.client.components.character.HeadComponent;
import com.mob.client.components.character.HeadingComponent;
import com.mob.client.components.TransformComponent;
import com.mob.client.handlers.CameraHandler;
import com.mob.client.interfaces.Constants;

/**
 * @author Rodrigo
 *
 */
public class CharacterRenderingSystem extends IteratingSystem implements Constants {

	// ===========================================================
	// Constants
	// ===========================================================
	public static final float PIXELS_TO_METERS = 1.0f / TILE_PIXEL_WIDTH;

	// ===========================================================
	// Fields
	// ===========================================================
	private ComponentMapper<BodyComponent> mBodyMapper;
	private ComponentMapper<HeadComponent> mHeadMapper;
	private ComponentMapper<TransformComponent> mTransformMapper;
	private ComponentMapper<CharacterComponent> mCharacterMapper;
	private ComponentMapper<HeadingComponent> mHeadingMapper;
	private ComponentMapper<ColorComponent> mColorMapper;
	
	private SpriteBatch mBatch;
	private Array<Entity> mRenderQueue;
	private Comparator<Entity> mComparator;

	// ===========================================================
	// Constructors
	// ===========================================================
	@SuppressWarnings("unchecked")
	public CharacterRenderingSystem(SpriteBatch pBatch) {
		super(Family.all(BodyComponent.class, 
						HeadComponent.class,
						TransformComponent.class, 
						CharacterComponent.class,
						HeadingComponent.class,
						ColorComponent.class)
				.get());

		// Obtenemos nuestros Mappers
		this.mBodyMapper = ComponentMapper.getFor(BodyComponent.class);
		this.mHeadMapper = ComponentMapper.getFor(HeadComponent.class);
		this.mTransformMapper = ComponentMapper.getFor(TransformComponent.class);
		this.mCharacterMapper = ComponentMapper.getFor(CharacterComponent.class);
		this.mHeadingMapper = ComponentMapper.getFor(HeadingComponent.class);
		this.mColorMapper = ComponentMapper.getFor(ColorComponent.class);
		
		// Creamos la render queue
		this.mRenderQueue = new Array<Entity>();
		
		// Creamos el comparator para decidir cual entidad tiene m�s prioridad
		this.mComparator = new Comparator<Entity>() {
			@Override
			public int compare(Entity entityA, Entity entityB) {
				return (int)Math.signum(mTransformMapper.get(entityB).pos.z -
										mTransformMapper.get(entityA).pos.z);
			}
		};
		
		// Asignamos nuestro puntero al spritebatch
		this.mBatch = pBatch;
		
	}

	// ===========================================================
	// Methods
	// ===========================================================
	

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		
		this.mRenderQueue.add(entity);

	}
	
	/**
	 * Este metodo se encarga de renderizar los characters con todos 
	 * sus components. Se ejecuta una vez por cada ciclo del engine
	 * y va iterando una render queue interna hasta que se dibujen
	 * todas las entities.
	 * 
	 * @author rt
	 * @param deltaTime
	 */
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		// Obtenemos una entity de la queue y inicializamos el batch
		this.mRenderQueue.sort(this.mComparator);
		CameraHandler.getCamera().update();
		this.mBatch.setProjectionMatrix(CameraHandler.getCamera().combined);
		this.mBatch.begin();
		
		// Iteramos todos los characters
		for (Entity entity : this.mRenderQueue) {
			
			// Obtenemos los components del character
			BodyComponent body = this.mBodyMapper.get(entity);
			HeadComponent head = this.mHeadMapper.get(entity);
			ColorComponent color = this.mColorMapper.get(entity);
			HeadingComponent heading = this.mHeadingMapper.get(entity);
			TransformComponent t = this.mTransformMapper.get(entity);
			
			// Separamos las TextureRegion's en uso
			TextureRegion bodyRegion = body.animations.get(heading.current).getAnimatedGraphic(true);
			TextureRegion headRegion = head.animations.get(heading.current).getGraphic();
			
			// Renderizamos el character
			Color previousColor = this.mBatch.getColor();
			this.mBatch.setColor(color.tint);
		
			// Preparamos variables para el render
			float bodyPixelOffsetX = 0.0f, bodyPixelOffsetY = 0.0f, headPixelOffsetX = 0.0f, headPixelOffsetY = 0.0f;
			
			// Si tiene un body
			if(bodyRegion != null) {
				bodyPixelOffsetX = t.pos.x - 32.0f;// - bodyRegion.getRegionWidth() * 0.5f;
				bodyPixelOffsetY = t.pos.y - (bodyRegion.getRegionHeight() - 32.0f) - 32.0f;
				
				this.mBatch.draw(bodyRegion, bodyPixelOffsetX, bodyPixelOffsetY);
			}
			
			// Si tiene head
			if(headRegion != null) {
				headPixelOffsetX = bodyPixelOffsetX + 5.0f;
				headPixelOffsetY = bodyPixelOffsetY - 9.0f;
				
				this.mBatch.draw(headRegion, headPixelOffsetX, headPixelOffsetY);
			}
			
			// Devolvemos el color original de la escena
			this.mBatch.setColor(previousColor);
		}
		
		// Finalizamos el batch
		this.mBatch.end();
		this.mRenderQueue.clear();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
