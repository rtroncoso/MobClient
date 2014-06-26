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
/**
 * This is our main class. It gets called by the launcher. 
 * We load all resources and init everything here.
 * @author Rodrigo Troncoso
 * @version 0.1
 */
package com.mob.client;

import com.badlogic.gdx.Gdx;  
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch; 
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mob.client.handlers.SurfaceHandler;
import com.mob.client.interfaces.Constants;
import com.mob.client.handlers.DataHandler;
  
  
public class MobClient extends Game implements Constants {  

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
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
    @Override  
    public void create() {  
        
        // We render using YDown instead of YUp
        this.mCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());  
        this.mCamera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); 
        //this.mCamera.position.set(Gdx.graphics.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.5f, 0);  
        
        // Calculate camera zoom according to aspect ratio
		this.mCamera.zoom = (Gdx.graphics.getWidth() / Gdx.graphics.getHeight()) * (GAME_SCREEN_ZOOM / ((Gdx.graphics.getWidth() / Gdx.graphics.getHeight()))); 
        
		// Set loading screen
        this.setScreen("LoadingScreen");  

        // Load INITs
        this.mDataHandler = new DataHandler();
        
        // Init texture engine
        this.setSurfaceHandler(new SurfaceHandler(this));
        this.getSurfaceHandler().setGraphicsPath(GAME_GRAPHICS_PATH);
          
        // Init spritebatch
        this.mSpriteBatch = new SpriteBatch();
        
        // Load font
        Texture text = new Texture(Gdx.files.internal(GAME_FONTS_PATH + "tahoma-bold.png"));
        this.mFont = new BitmapFont(Gdx.files.internal("data/fonts/tahoma-bold.fnt"), new TextureRegion(text), true);
        this.mFont.getRegion().getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
          
        // Load testing screen
        this.setScreen("TestScreen");  
          
    }  
    
    @Override  
    public void dispose() {  
        if (mCurrentScreen != null) mCurrentScreen.dispose();  
    }  
  
    @Override  
    public void pause() {  
        if (mCurrentScreen != null) mCurrentScreen.pause();  
    }  
  
    @Override  
    public void render() {  
        if (mCurrentScreen != null) {  
            mCurrentScreen.update(Gdx.graphics.getRawDeltaTime());  
        } else {  
              
        	Gdx.gl.glClearColor(0, 0, 0, 1);  
        	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  
        }  
    }  
  
    @Override  
    public void resize(int arg0, int arg1) {  
          if(mCurrentScreen != null) mCurrentScreen.resize(arg0, arg1);
    }  
  
    @Override  
    public void resume() {  
        if (mCurrentScreen != null) mCurrentScreen.resume();  
    }

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}  

	// ===========================================================
	// Getter & Setter
	// ===========================================================


	// ===========================================================
	// Methods
	// ===========================================================


	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
  

}  