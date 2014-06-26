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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Vector;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mob.client.data.BodyData;
import com.mob.client.data.FxData;
import com.mob.client.data.GrhData;
import com.mob.client.data.HeadData;
import com.mob.client.data.HelmetData;
import com.mob.client.data.ShieldData;
import com.mob.client.data.WeaponData;
import com.mob.client.engine.PhysicsEngine;
import com.mob.client.handlers.CharacterHandler;
import com.mob.client.handlers.DataHandler;
import com.mob.client.handlers.MapHandler;
import com.mob.client.handlers.SurfaceHandler;
import com.mob.client.interfaces.Constants;
import com.mob.client.screens.Screen;
  
public abstract class Game implements ApplicationListener, Constants {  

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
    protected DataHandler mDataHandler;

	protected PhysicsEngine mEngine;

	protected Screen mCurrentScreen;
    
    protected BitmapFont mFont;

	// ===========================================================
	// Constructors
	// ===========================================================
    public Game() {

        this.mMapHandler = new MapHandler(this);
        this.mCharacterHandler = new CharacterHandler(this);

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
	public Vector<ShieldData> getShieldData() {
		return this.mDataHandler.getShieldData();
	}

	/**
	 * @param mShieldData the mShieldData to set
	 */
	public void setShieldData(Vector<ShieldData> mShieldData) {
		this.mDataHandler.setShieldData(mShieldData);
	}
    /**
	 * @return the mWeaponData
	 */
	public Vector<WeaponData> getWeaponData() {
		return this.mDataHandler.getWeaponData();
	}

	/**
	 * @param mWeaponData the mWeaponData to set
	 */
	public void setWeaponData(Vector<WeaponData> mWeaponData) {
		this.mDataHandler.setWeaponData(mWeaponData);
	}
	
	/**
	* @return the _grhData
	*/
	public Vector<GrhData> getGrhData() {
		return this.mDataHandler.getGrhData();
	}
	
	/**
	* @param _grhData the _grhData to set
	*/
	public void setGrhData(Vector<GrhData> _grhData) {
		this.mDataHandler.setGrhData(_grhData);
	}
	
	/**
	* @return the _bodyData
	*/
	public Vector<BodyData> getBodyData() {
		return this.mDataHandler.getBodyData();
	}
	
	/**
	* @param _bodyData the _bodyData to set
	*/
	public void setBodyData(Vector<BodyData> _bodyData) {
		this.mDataHandler.setBodyData(_bodyData);
	}

	/**
	 * @return the _headData
	 */
	public Vector<HeadData> getHeadData() {
		return this.mDataHandler.getHeadData();
	}

	/**
	 * @param _headData the _headData to set
	 */
	public void setHeadData(Vector<HeadData> _headData) {
		this.mDataHandler.setHeadData(_headData);
	}

	/**
	 * @return the _helmetData
	 */
	public Vector<HelmetData> getHelmetData() {
		return this.mDataHandler.getHelmetData();
	}

	/**
	 * @param _helmetData the _helmetData to set
	 */
	public void setHelmetData(Vector<HelmetData> _helmetData) {
		this.mDataHandler.setHelmetData(_helmetData);
	}
	
	/**
	 * @return the mFxData
	 */
	public Vector<FxData> getFxData() {
		return this.mDataHandler.getFxData();
	}

	/**
	 * @param mFxData the mFxData to set
	 */
	public void setFxData(Vector<FxData> mFxData) {
		this.mDataHandler.setFxData(mFxData);
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