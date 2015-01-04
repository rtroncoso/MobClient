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
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mob.client.components.BodyComponent;
import com.mob.client.components.CharacterComponent;
import com.mob.client.components.ColorComponent;
import com.mob.client.components.HeadingComponent;
import com.mob.client.components.TransformComponent;
import com.mob.client.interfaces.ConstantsInterface;

/**
 * @author Rodrigo
 *
 */
public class CharacterRenderingSystem extends IteratingSystem implements ConstantsInterface {

	// ===========================================================
	// Constants
	// ===========================================================
	public static final float PIXELS_TO_METERS = 1.0f / TILE_PIXEL_WIDTH;

	// ===========================================================
	// Fields
	// ===========================================================
	private ComponentMapper<BodyComponent> mBodyMapper;
	private ComponentMapper<TransformComponent> mTransformMapper;
	private ComponentMapper<CharacterComponent> mCharacterMapper;
	private ComponentMapper<HeadingComponent> mHeadingMapper;
	private ComponentMapper<ColorComponent> mColorMapper;
	
	private SpriteBatch mBatch;
	private Array<Entity> mRenderQueue;
	private Comparator<Entity> mComparator;
	private OrthographicCamera mCamera;

	// ===========================================================
	// Constructors
	// ===========================================================
	@SuppressWarnings("unchecked")
	public CharacterRenderingSystem(SpriteBatch pBatch) {
		super(Family.all(BodyComponent.class, 
					TransformComponent.class, 
					CharacterComponent.class,
					HeadingComponent.class,
					ColorComponent.class)
				.get());

		// Obtenemos nuestros Mappers
		this.mBodyMapper = ComponentMapper.getFor(BodyComponent.class);
		this.mTransformMapper = ComponentMapper.getFor(TransformComponent.class);
		this.mCharacterMapper = ComponentMapper.getFor(CharacterComponent.class);
		this.mHeadingMapper = ComponentMapper.getFor(HeadingComponent.class);
		this.mColorMapper = ComponentMapper.getFor(ColorComponent.class);
		
		// Creamos la render queue
		this.mRenderQueue = new Array<Entity>();
		
		// Creamos el comparator para decidir cual entidad tiene más prioridad
		this.mComparator = new Comparator<Entity>() {
			@Override
			public int compare(Entity entityA, Entity entityB) {
				return (int)Math.signum(mTransformMapper.get(entityB).pos.z -
										mTransformMapper.get(entityA).pos.z);
			}
		};
		
		// Asignamos cosas necesarias para el engine
		this.mBatch = pBatch;
		this.mCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.mCamera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        this.mCamera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); 
		
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
		this.mCamera.update();
		this.mBatch.setProjectionMatrix(this.mCamera.combined);
		this.mBatch.begin();
		
		// Iteramos todos los characters
		for (Entity entity : this.mRenderQueue) {
			
			// Obtenemos los components del character
			BodyComponent body = this.mBodyMapper.get(entity);
			ColorComponent color = this.mColorMapper.get(entity);
			HeadingComponent heading = this.mHeadingMapper.get(entity);
			TextureRegion bodyRegion = body.animations.get(heading.current).getAnimatedGraphic(true);
			
			// Si no tiene TextureRegion
			if (bodyRegion == null) {
				continue;
			}
			
			// Obtenemos las coordenadas del character
			TransformComponent t = this.mTransformMapper.get(entity);
		
			// Preparamos variables para el render
			float width = bodyRegion.getRegionWidth();
			float height = bodyRegion.getRegionHeight();
			float originX = width * 0.5f;
			float originY = height * 0.5f;
			
			// Renderizamos el character
			Color previousColor = this.mBatch.getColor();
			this.mBatch.setColor(color.tint);
			this.mBatch.draw(bodyRegion, t.pos.x - originX, t.pos.y - originY);
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
