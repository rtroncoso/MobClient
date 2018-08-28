/**
 * Copyright (C) 2014  Rodrigo Troncoso
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p>
 * Manages loader objects
 *
 * @author Rodrigo Troncoso
 * @version 0.1
 * @author Rodrigo Troncoso
 * @version 0.1
 * @author Rodrigo Troncoso
 * @version 0.1
 * @author Rodrigo Troncoso
 * @version 0.1
 * @author Rodrigo Troncoso
 * @version 0.1
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-06-26
 * <p>
 * Manages loader objects
 * @since 2014-06-26
 * <p>
 * Manages loader objects
 * @since 2014-06-26
 * <p>
 * Manages loader objects
 * @since 2014-06-26
 * <p>
 * Manages loader objects
 * @since 2014-06-26
 * <p>
 * Manages loader objects
 * @since 2014-06-26
 */
/**
 * Manages loader objects
 * @author Rodrigo Troncoso
 * @version 0.1
 * @since 2014-06-26
 */
package com.mob.client.handlers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.mob.dao.descriptors.*;
import com.mob.dao.objects.*;
import com.mob.dao.readers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DescriptorsHandler {

    private static Map<String, Graphic> graphics;
    private static List<BodyDescriptor> bodies;
    private static List<HeadDescriptor> heads;
    private static List<HelmetDescriptor> helmets;
    private static List<WeaponDescriptor> weapons;
    private static List<ShieldDescriptor> shields;
    private static List<FXDescriptor> fxs;
    private static DescriptorsReader reader = new AODescriptorsReader();

    public static void load() {
        graphics = loadMap("graphicsMap");
        bodies = load("bodies2");
        weapons = load("weapons2");
        shields = load("shields2");
        heads = load("heads2");
        helmets = load("helmets2");
        fxs = load("fxs2");
    }

    public static List load(String fileName) {
        Json json = getJson();
        FileHandle file = Gdx.files.internal("data/descriptors/" + fileName + ".json");
        return (List) json.fromJson(ArrayList.class, file);
    }

    public static Map loadMap(String fileName) {
        Json json = getJson();
        FileHandle file = Gdx.files.internal("data/descriptors/" + fileName + ".json");
        return (java.util.HashMap) json.fromJson(HashMap.class, file);
    }
//
//    public static IntMap loadGraphics(String fileName) {
//        Json json = getJson();
//        FileHandle file = Gdx.files.internal("data/descriptors/" + fileName + ".json");
//        return (IntMap) json.fromJson(IntMap.class, file);
//    }

    private static Json getJson() {
        Json json = new Json();
        json.addClassTag("graphics", Graphic.class);
        json.addClassTag("bodies", BodyDescriptor.class);
        json.addClassTag("heads", HeadDescriptor.class);
        json.addClassTag("helmets", HelmetDescriptor.class);
        json.addClassTag("weapons", WeaponDescriptor.class);
        json.addClassTag("shields", ShieldDescriptor.class);
        json.addClassTag("fxs", FXDescriptor.class);
        return json;
    }

    public static Map<String, Graphic> getGraphics() {
        return graphics;
    }

    public static List<BodyDescriptor> getBodies() {
        return bodies;
    }

    public static List<FXDescriptor> getFxs() {
        return fxs;
    }

    public static List<HeadDescriptor> getHeads() {
        return heads;
    }

    public static List<HelmetDescriptor> getHelmets() {
        return helmets;
    }

    public static List<ShieldDescriptor> getShields() {
        return shields;
    }

    public static List<WeaponDescriptor> getWeapons() {
        return weapons;
    }

    public static BodyDescriptor getBody(int index) {
        return DescriptorsHandler.getBodies().get(index);
    }

    public static HeadDescriptor getHead(int index) {
        return DescriptorsHandler.getHeads().get(index);
    }

    public static HelmetDescriptor getHelmet(int index) {
        return DescriptorsHandler.getHelmets().get(index);
    }

    public static FXDescriptor getFX(int index) {
        return DescriptorsHandler.getFxs().get(index);
    }

    public static ShieldDescriptor getShield(int index) {
        return DescriptorsHandler.getShields().get(index);
    }

    public static WeaponDescriptor getWeapon(int index) {
        return DescriptorsHandler.getWeapons().get(index);
    }

    public static Graphic getGraphic(int index) {
        return DescriptorsHandler.getGraphics().get(String.valueOf(index));
    }


}
