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
package com.mob.client.artemis.systems.render;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mob.client.artemis.components.graphics.LineComponent;
import com.mob.client.artemis.systems.camera.CameraSystem;

/**
 * GridRenderingSystem Class
 * @author rt
 * @package com.mob.client.api.systems.render
 */
@Wire
public class GridRenderingSystem extends EntityProcessingSystem {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private ComponentMapper<LineComponent> lm;
    private CameraSystem mCameraSystem;
	private ShapeRenderer mShapeRenderer = new ShapeRenderer();

	// ===========================================================
	// Constructors
	// ===========================================================
    @SuppressWarnings("unchecked")
	public GridRenderingSystem() {
		super(Aspect.getAspectForAll(LineComponent.class));

		// Necesario para el ShapeRenderer
		this.mShapeRenderer.setAutoShapeType(true);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

    @Override
    protected void begin() {
        super.begin();

        // Inicializamos el batch y lo necesario para renderizar
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        this.mCameraSystem.camera.update();
        this.mShapeRenderer.setProjectionMatrix(this.mCameraSystem.camera.combined);
        this.mShapeRenderer.begin();

    }

    /**
     * Este metodo se encarga de renderizar las
     * líneas de merca de nuestro GridSystem
     *
     * @author rt
     * @param entity
     */
    @Override
    protected void process(Entity entity) {
        // Obtenemos el component de cada línea
        LineComponent line = this.lm.get(entity);

        // Dibujamos la linea actual
        this.mShapeRenderer.setColor(new Color(0, 255, 0, 0.5f));
        this.mShapeRenderer.line(line.start.x, line.start.y, line.end.x, line.end.y);
    }

    @Override
    protected void end() {
        super.end();

        // Finalizamos el ShapeRenderer y limpiamos
        this.mShapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    // ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
