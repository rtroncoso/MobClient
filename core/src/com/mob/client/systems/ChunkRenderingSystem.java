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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mob.client.components.*;
import com.mob.client.handlers.CameraHandler;
import com.mob.client.interfaces.ConstantsInterface;

import javax.xml.crypto.dsig.Transform;
import java.util.Comparator;

/**
 * @author Rodrigo
 *
 */
public class ChunkRenderingSystem extends IteratingSystem implements ConstantsInterface {

	// ===========================================================
	// Constants
	// ===========================================================
	public static final float PIXELS_TO_METERS = 1.0f / TILE_PIXEL_WIDTH;

	// ===========================================================
	// Fields
	// ===========================================================
	private ComponentMapper<TransformComponent> mTransformMapper;
	private ComponentMapper<ChunkComponent> mChunkComponent;

	private SpriteBatch mBatch;
	private Array<Entity> mRenderQueue;
	private Comparator<Entity> mComparator;

	// ===========================================================
	// Constructors
	// ===========================================================
	@SuppressWarnings("unchecked")
	public ChunkRenderingSystem(SpriteBatch pBatch) {
		super(Family.all(TransformComponent.class,
						ChunkComponent.class)
				.get());

		// Obtenemos nuestros Mappers
		this.mTransformMapper = ComponentMapper.getFor(TransformComponent.class);
		this.mChunkComponent = ComponentMapper.getFor(ChunkComponent.class);
		
		// Creamos la render queue
		this.mRenderQueue = new Array<Entity>();
		
		// Creamos el comparator para decidir cual entidad tiene m�s prioridad
		this.mComparator = new Comparator<Entity>() {
			@Override
			public int compare(Entity entityA, Entity entityB) {
				return new Float(mTransformMapper.get(entityA).pos.x)
						.compareTo(mTransformMapper.get(entityB).pos.x);
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
	 * Este metodo se encarga de renderizar todos los chunks
	 * del juego. Estos son creados al inicializarse un mapa
	 * usando ChunkFactory
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

		// Todas las calls al renderer irian aca
		this.renderWorld(deltaTime);

		// Finalizamos el batch
		this.mBatch.end();
		this.mRenderQueue.clear();
	}

	/**
	 * Renderizamos todos los objetos del mundo (asumiendo que
	 * ya hay un deltaTime establecido y que se inicializo el
	 * batch.
	 *
	 * @param deltaTime
	 */
	public void renderWorld(float deltaTime) {

		// Iteramos todas las layers del chunk y las renderizamos
		for(int layer = 0; layer < ChunkComponent.CHUNK_LAYERS; layer++) {

			// Iteramos todos los chunks
			for (Entity entity : this.mRenderQueue) {

				// Obtenemos los components del chunk
				ChunkComponent chunk = this.mChunkComponent.get(entity);
				TransformComponent t = this.mTransformMapper.get(entity);

				for(int y = 1; y <= ChunkComponent.CHUNK_TILE_SIZE; y++) {
					for(int x = 1; x <= ChunkComponent.CHUNK_TILE_SIZE; x++) {

						// Obtenemos los values para esta layer
						TextureRegion tileRegion = chunk.tiles[x][y].getRegion(layer);
						float tileOffsetX = (t.pos.x * TileComponent.TILE_SIZE) + (x * TileComponent.TILE_SIZE) - TileComponent.TILE_SIZE;
						float tileOffsetY = (t.pos.y * TileComponent.TILE_SIZE) + (y * TileComponent.TILE_SIZE) - TileComponent.TILE_SIZE;

						// Si tiene region
						if(tileRegion != null) {
							this.mBatch.draw(tileRegion, tileOffsetX, tileOffsetY);
						}
					}
				}
			}
		}

	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
