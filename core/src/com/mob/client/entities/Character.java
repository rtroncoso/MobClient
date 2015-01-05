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
package com.mob.client.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mob.client.components.BodyComponent;
import com.mob.client.components.ColorComponent;
import com.mob.client.components.HeadComponent;
import com.mob.client.components.MovementComponent;
import com.mob.client.components.PositionComponent;
import com.mob.client.components.TransformComponent;
import com.mob.client.data.BodyData;
import com.mob.client.data.GrhData;
import com.mob.client.data.HeadData;
import com.mob.client.handlers.AssetsHandler;
import com.mob.client.interfaces.DisposableInterface;
import com.mob.client.textures.BundledAnimation;

/**
 * Instancia de la entity en la que se guardan 
 * los punteros a los components necesarios
 * 
 * @author Rodrigo
 */
public class Character extends Entity implements DisposableInterface {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private MovementComponent mMovementComponent = new MovementComponent();
	private TransformComponent mTransformComponent = new TransformComponent();
	private PositionComponent mPositionComponent = new PositionComponent();
	private ColorComponent mColorComponent = new ColorComponent();
	private BodyComponent mBodyComponent = new BodyComponent();
	private HeadComponent mHeadComponent = new HeadComponent();

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * Se encarga de cargar un body a nuestro entity
	 * 
	 * @param body
	 */
	public void setBody(BodyData body) {
		
		// Iteramos nuestros headings
		int index = 0;
		for(int grhIndex : body.getBodyArray()) {
			
			// Obtenemos GrhData necesario
			GrhData grh = AssetsHandler.getGrh(grhIndex);
			
			// Agregamos las animations
			this.mBodyComponent.animations.put(index, new BundledAnimation(grh));
			index++;
		}
		
		// Agregamos el component a nuestra entity y sacamos el viejo
		this.remove(BodyComponent.class);
		this.add(this.mBodyComponent);
	}

	/**
	 * Se encarga de cargar una head a nuestro entity
	 * 
	 * @param head
	 */
	public void setHead(HeadData head) {
		
		// Iteramos los headings
		int index = 0;
		for(int grhIndex : head.getHeadIndex()) {
			
			// Obtenemos GrhData necesario
			GrhData grh = AssetsHandler.getGrh(grhIndex);
			
			// Agregamos las animations
			this.getHeadComponent().animations.put(index, new BundledAnimation(grh));
			index++;
		}
		
		// Agregamos el component a nuestra entity y sacamos el viejo
		this.remove(HeadComponent.class);
		this.add(this.getHeadComponent());
		
	}
	
	/**
	 * Obtiene el vector de velocidad de un character
	 * 
	 * @return la velocidad del character
	 */
	public Vector2 getVelocity() {
		return this.mMovementComponent.velocity;
	}
	
	/**
	 * Settea la velocidad vertical y horizontal de un character
	 * 
	 * @param pX
	 * @param pY
	 */
	public void setVelocity(float pX, float pY) {
		this.getMovementComponent().velocity.set(pX, pY);
	}
	
	/**
	 * Obtiene Vector2 de un character
	 * 
	 * @return la velocidad del character
	 */
	public Vector3 getPosition() {
		return this.mPositionComponent.get();
	}
	
	/**
	 * Settea la velocidad vertical y horizontal de un character
	 * 
	 * @param position
	 */
	public void setPosition(Vector3 position) {
		this.getPositionComponent().set(position);
	}
	
	/**
	 * @return el color del character
	 */
	public Color getColor() {
		return this.getColorComponent().tint;
	}
	
	/**
	 * Settea el color de nuestro character
	 * 
	 * @param tint
	 */
	public void setColor(Color tint) {
		this.getColorComponent().tint = tint;
	}
	
	/**
	 * @return the mBodyComponent
	 */
	public BodyComponent getBodyComponent() {
		return mBodyComponent;
	}

	/**
	 * @param mBodyComponent the mBodyComponent to set
	 */
	public void setBodyComponent(BodyComponent mBodyComponent) {
		this.mBodyComponent = mBodyComponent;
	}

	/**
	 * @return the mHeadComponent
	 */
	private HeadComponent getHeadComponent() {
		return mHeadComponent;
	}

	/**
	 * @param mHeadComponent the mHeadComponent to set
	 */
	private void setHeadComponent(HeadComponent mHeadComponent) {
		this.mHeadComponent = mHeadComponent;
	}

	/**
	 * @return the mMovementComponent
	 */
	public MovementComponent getMovementComponent() {
		return mMovementComponent;
	}

	/**
	 * @param mMovementComponent the mMovementComponent to set
	 */
	public void setMovementComponent(MovementComponent mMovementComponent) {
		this.mMovementComponent = mMovementComponent;
	}

	/**
	 * @return the mColorComponent
	 */
	public ColorComponent getColorComponent() {
		return mColorComponent;
	}

	/**
	 * @param mColorComponent the mColorComponent to set
	 */
	public void setColorComponent(ColorComponent mColorComponent) {
		this.mColorComponent = mColorComponent;
	}

	/**
	 * @return the mTransformComponent
	 */
	public TransformComponent getTransformComponent() {
		return mTransformComponent;
	}

	/**
	 * @param mTransformComponent the mTransformComponent to set
	 */
	public void setTransformComponent(TransformComponent mTransformComponent) {
		this.mTransformComponent = mTransformComponent;
	}

	/**
	 * @return the mPositionComponent
	 */
	public PositionComponent getPositionComponent() {
		return mPositionComponent;
	}

	/**
	 * @param mPositionComponent the mPositionComponent to set
	 */
	public void setPositionComponent(PositionComponent mPositionComponent) {
		this.mPositionComponent = mPositionComponent;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
