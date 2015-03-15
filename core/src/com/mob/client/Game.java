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
 * Game's main class. 
 * @author Rodrigo Troncoso
 * @version 0.1
 */
package com.mob.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mob.client.data.*;
import com.mob.client.engine.PhysicsEngine;
import com.mob.client.handlers.AssetsHandler;
import com.mob.client.handlers.CharacterHandler;
import com.mob.client.handlers.MapHandler;
import com.mob.client.handlers.SurfaceHandler;
import com.mob.client.interfaces.ConstantsInterface;
import com.mob.client.screens.Screen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Vector;
  
public abstract class Game implements ApplicationListener, ConstantsInterface {  

	// ===========================================================
	// Constants
	// ===========================================================


	// ===========================================================
	// Fields
	// ===========================================================
    protected OrthographicCamera mCamera;  
    protected HashMap<String, Screen> mScreens;  
    protected SpriteBatch mSpriteBatch;  
    
	protected MapHandler mMapHandler;
    protected SurfaceHandler mSurfaceHandler;
    protected CharacterHandler mCharacterHandler;
    protected AssetsHandler mDataHandler;

	protected PhysicsEngine mEngine;

	protected Screen mCurrentScreen;
    
    protected BitmapFont mFont;

	// ===========================================================
	// Constructors
	// ===========================================================
    public Game() {

        this.mMapHandler = new MapHandler(this);

    	this.mScreens = new HashMap<String, Screen>();  
        this.mEngine = new PhysicsEngine(this);
    }

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
    /**
     * Init everything
     */
    public abstract void create();
    public abstract void update (float dt);
    public abstract void dispose();
    public abstract void pause();
    public abstract void render();
    public abstract void resize(int arg0, int arg1);
    public abstract void resume();
    
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	/**
	 * @return the mShieldData
	 */
	public Vector<Shield> getShieldData() {
		return AssetsHandler.getShieldData();
	}

	/**
	 * @param mShield the mShield to set
	 */
	public void setShieldData(Vector<Shield> mShield) {
		AssetsHandler.setShieldData(mShield);
	}
    /**
	 * @return the mWeaponData
	 */
	public Vector<Weapon> getWeaponData() {
		return AssetsHandler.getWeaponData();
	}

	/**
	 * @param mWeapon the mWeapon to set
	 */
	public void setWeaponData(Vector<Weapon> mWeapon) {
		AssetsHandler.setWeaponData(mWeapon);
	}
	
	/**
	* @return the _grhData
	*/
	public Vector<Grh> getGrhData() {
		return AssetsHandler.getGrhData();
	}
	
	/**
	* @param _grh the _grh to set
	*/
	public void setGrhData(Vector<Grh> _grh) {
		AssetsHandler.setGrhData(_grh);
	}
	
	/**
	* @return the _bodyData
	*/
	public Vector<Body> getBodyData() {
		return AssetsHandler.getBodyData();
	}
	
	/**
	* @param _body the _body to set
	*/
	public void setBodyData(Vector<Body> _body) {
		AssetsHandler.setBodyData(_body);
	}

	/**
	 * @return the _headData
	 */
	public Vector<Head> getHeadData() {
		return AssetsHandler.getHeadData();
	}

	/**
	 * @param _head the _head to set
	 */
	public void setHeadData(Vector<Head> _head) {
		AssetsHandler.setHeadData(_head);
	}

	/**
	 * @return the _helmetData
	 */
	public Vector<Helmet> getHelmetData() {
		return AssetsHandler.getHelmetData();
	}

	/**
	 * @param _helmet the _helmet to set
	 */
	public void setHelmetData(Vector<Helmet> _helmet) {
		AssetsHandler.setHelmetData(_helmet);
	}
	
	/**
	 * @return the mFxData
	 */
	public Vector<Fx> getFxData() {
		return AssetsHandler.getFxData();
	}

	/**
	 * @param mFx the mFx to set
	 */
	public void setFxData(Vector<Fx> mFx) {
		AssetsHandler.setFxData(mFx);
	}

	/**
	 * @return the _surfaceHandler
	 */
	public SurfaceHandler getSurfaceHandler() {
		return mSurfaceHandler;
	}

	/**
	 * @param _surfaceHandler the _surfaceHandler to set
	 */
	public void setSurfaceHandler(SurfaceHandler _surfaceHandler) {
		this.mSurfaceHandler = _surfaceHandler;
	}

	/**
	 * @return the mCamera
	 */
	public OrthographicCamera getCamera() {
		return mCamera;
	}

	/**
	 * @param mCamera the mCamera to set
	 */
	public void setCamera(OrthographicCamera mCamera) {
		this.mCamera = mCamera;
	}

    /**
	 * @return the mMapHandler
	 */
	public MapHandler getMapHandler() {
		return mMapHandler;
	}

	/**
	 * @param mMapHandler the mMapHandler to set
	 */
	public void setMapHandler(MapHandler mMapHandler) {
		this.mMapHandler = mMapHandler;
	}

	/**
	 * @return the mSpriteBatch
	 */
	public SpriteBatch getSpriteBatch() {
		return mSpriteBatch;
	}

	/**
	 * @param mSpriteBatch the mSpriteBatch to set
	 */
	public void setSpriteBatch(SpriteBatch mSpriteBatch) {
		this.mSpriteBatch = mSpriteBatch;
	}
	
	/**
	 * @return the mCurrentMap
	 */
	public PhysicsEngine getEngine() {
		return mEngine;
	}

	/**
	 * @param mCurrentMap the mCurrentMap to set
	 */
	public void setEngine(PhysicsEngine mCurrentMap) {
		this.mEngine = mCurrentMap;
	}    
	
	/**
	 * @return the mCharacterHandler
	 */
	public CharacterHandler getCharacterHandler() {
		return mCharacterHandler;
	}

	/**
	 * @param mCharacterHandler the mCharacterHandler to set
	 */
	public void setCharacterHandler(CharacterHandler mCharacterHandler) {
		this.mCharacterHandler = mCharacterHandler;
	}

	/**
	 * @return the mFont
	 */
	public BitmapFont getFont() {
		return mFont;
	}

	/**
	 * @param mFont the mFont to set
	 */
	public void setFont(BitmapFont mFont) {
		this.mFont = mFont;
	}
	
	/**
	 * @return the mCurrentScreen
	 */
	public Screen getCurrentScreen() {
		return mCurrentScreen;
	}

	/**
	 * @param mCurrentScreen the mCurrentScreen to set
	 */
	public void setCurrentScreen(Screen mCurrentScreen) {
		this.mCurrentScreen = mCurrentScreen;
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	public void setScreen (String screenClassName) {  
        
        screenClassName = "com.mob.client.screens."+screenClassName;  
        Screen newScreen = null;  
          
        if (this.mScreens.containsKey(screenClassName) == false) {  
              
            try {  
                Class<?> screenClass =  Class.forName(screenClassName);   
                Constructor<?> constructor = screenClass.getConstructor(Game.class);      
                newScreen = (Screen) constructor.newInstance(this);  
                this.mScreens.put(screenClassName, newScreen);  
            } catch ( InvocationTargetException ex ){  
                System.err.println( ex + " Screen with Wrong args in Constructor.");  
            } catch ( NoSuchMethodException ex ){  
            } catch ( ClassNotFoundException ex ){  
              System.err.println( ex + " Screen Class Not Found.");  
            } catch( InstantiationException ex ){  
              System.err.println( ex + " Screen Must be a concrete class.");  
            } catch( IllegalAccessException ex ){  
              System.err.println( ex + " Screen with Wrong number of args.");  
            }  
        } else {  
            newScreen = this.mScreens.get(screenClassName);  
        }  
          
        if (newScreen == null) return;  
          
        if (this.mCurrentScreen != null) {  
            //remove current screen!  
            mCurrentScreen.destroy();  
        }  
        this.mCurrentScreen = newScreen;  
        this.mCurrentScreen.createScreen();  
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}  