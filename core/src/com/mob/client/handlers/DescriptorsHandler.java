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
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.LongMap;
import com.google.gson.reflect.TypeToken;
import com.mob.dao.descriptors.*;
import com.mob.dao.objects.*;
import com.mob.dao.readers.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

public class DescriptorsHandler {

    private static LongMap<Graphic> graphics;
    private static LongMap<BodyDescriptor> bodies;
    private static LongMap<HeadDescriptor> heads;
    private static LongMap<HelmetDescriptor> helmets;
    private static LongMap<WeaponDescriptor> weapons;
    private static LongMap<ShieldDescriptor> shields;
    private static LongMap<FXDescriptor> fxs;
    private static DescriptorsReader reader = new AODescriptorsReader();

    public static void load() {
        graphics = load("graphics");
        bodies = load("bodies");
        weapons = load("weapons");
        shields = load("shields");
        heads = load("heads");
        helmets = load("helmets");
        fxs = load("fxs");
    }

    public static LongMap load(String fileName) {
        Type type = new TypeToken<LongMap<Graphic>>() {}.getType();
        return getJson().fromJson(LongMap.class, Gdx.files.internal("data/descriptors/" + fileName + ".json"));
    }

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

//    public static void printJson(LongMap<?> map, String fileName) {
//        System.out.println("---- " + fileName + " ----");
//        try (PrintWriter out = new PrintWriter("data/descriptors/" + fileName + ".json")) {
//            Json json = new Json();
//            json.addClassTag(fileName, map.get(1).getClass());
//            out.println(json.toJson(map));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public static LongMap<Graphic> getGraphics() {
        return graphics;
    }

    public static LongMap<BodyDescriptor> getBodies() {
        return bodies;
    }

    public static LongMap<FXDescriptor> getFxs() {
        return fxs;
    }

    public static LongMap<HeadDescriptor> getHeads() {
        return heads;
    }

    public static LongMap<HelmetDescriptor> getHelmets() {
        return helmets;
    }

    public static LongMap<ShieldDescriptor> getShields() {
        return shields;
    }

    public static LongMap<WeaponDescriptor> getWeapons() {
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
        return DescriptorsHandler.getGraphics().get(index);
    }


}
