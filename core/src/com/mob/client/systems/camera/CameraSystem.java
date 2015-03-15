/*******************************************************************************
 * Copyright (C) 2015  Rodrigo Troncoso
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
package com.mob.client.systems.camera;

import com.artemis.annotations.Wire;
import com.artemis.systems.VoidEntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * CameraSystem Class
 *
 * @author rt
 * @package com.mob.client.systems.mCamera
 */
@Wire
public class CameraSystem extends VoidEntitySystem {

    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    public final OrthographicCamera camera;
    public final OrthographicCamera guiCamera;


    // ===========================================================
    // Constructors
    // ===========================================================
    /**
     * @param zoom How much
     */
    public CameraSystem( float zoom ) {

        float zoomFactorInverter = 1f/zoom;

        camera = new OrthographicCamera(Gdx.graphics.getWidth() * zoomFactorInverter, Gdx.graphics.getHeight() * zoomFactorInverter);
        camera.setToOrtho(true, Gdx.graphics.getWidth() * zoomFactorInverter, Gdx.graphics.getHeight() * zoomFactorInverter);
        camera.update();

        guiCamera = new OrthographicCamera(Gdx.graphics.getWidth() * zoomFactorInverter, Gdx.graphics.getHeight() * zoomFactorInverter);
        guiCamera.setToOrtho(true, Gdx.graphics.getWidth() * zoomFactorInverter, Gdx.graphics.getHeight() * zoomFactorInverter);
        guiCamera.update();
    }


    // ===========================================================
    // Methods
    // ===========================================================


    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    @Override
    protected void processSystem() {

    }


    // ===========================================================
    // Getter & Setter
    // ===========================================================


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


}
