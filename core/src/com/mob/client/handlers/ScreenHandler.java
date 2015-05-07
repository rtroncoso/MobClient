/**
 * ****************************************************************************
 * Copyright (C) 2014  Rodrigo Troncoso
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * *****************************************************************************
 */
package com.mob.client.handlers;

import com.mob.client.Game;
import com.mob.client.screens.Screen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ScreenHandler {

    protected static HashMap<String, Screen> screens = new HashMap<String, Screen>();
    protected static Screen currentScreen;
    private static Game game;

    public static Screen load(String screenClassName) {

        screenClassName = "com.mob.client.screens." + screenClassName;
        Screen newScreen = null;

        if(!screens.containsKey(screenClassName)) {
            try {
                Class<?> screenClass =  Class.forName(screenClassName);
                Constructor<?> constructor = screenClass.getConstructor(Game.class);
                newScreen = (Screen) constructor.newInstance(game);
                screens.put(screenClassName, newScreen);
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
            newScreen = screens.get(screenClassName);
        }

        if(currentScreen != null) {
            //remove current screen!
            currentScreen.dispose();
        }

        currentScreen = newScreen;
        return currentScreen;
    }

    /**
     * @param currentScreen the currentScreen to set
     */
    public static void setCurrent(Screen currentScreen) {
        currentScreen = currentScreen;
    }

    /**
     * @return the currentScreen
     */
    public static Screen getCurrent() {
        return ScreenHandler.currentScreen;
    }

    public static Game getGameInstance() {
        return game;
    }

    public static void setGameInstance(Game game) {
        ScreenHandler.game = game;
    }
}
