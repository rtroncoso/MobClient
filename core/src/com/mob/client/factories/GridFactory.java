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
import com.badlogic.gdx.utils.Array;
import com.mob.client.components.LineComponent;

/**
 * Se encarga de crear un grid system específico para
 * Argentum Online (tiles de 32px * 32px)
 * 
 * @author Rodrigo
 */
public class GridFactory {

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

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	public static Array<Entity> create() {
		
		// Declaramos cosas necesarias
		int horizontalLines = 100, verticalLines = 100;
		float horizontalStep = 32.0f, verticalStep = 32.0f;
		Array<Entity> lines = new Array<Entity>();
		
		// Iteramos sobre las lineas horizontales
		for(int i = 0; i < horizontalLines; i++) {
			
			// Creamos la entidad
			Entity line = new Entity();
			
			// Creamos su line component
			LineComponent linePosition = new LineComponent();
			linePosition.start.set(0.0f, i * horizontalStep);
			linePosition.end.set(100.0f * horizontalStep, i * horizontalStep);
			line.add(linePosition);
			
			// Agregamos la line al array
			lines.add(line);
		}
		
		// Iteramos sobre las lineas verticales
		for(int i = 0; i < verticalLines; i++) {
			
			// Creamos la entidad
			Entity line = new Entity();
			
			// Creamos su line component
			LineComponent linePosition = new LineComponent();
			linePosition.start.set((float) i * verticalStep, 0.0f);
			linePosition.end.set(i * verticalStep, 100.0f * verticalStep);
			line.add(linePosition);
			
			// Agregamos la line al array
			lines.add(line);
		}
		
		return lines;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
