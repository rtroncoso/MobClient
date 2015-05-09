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
package com.mob.dao.readers;

import com.badlogic.gdx.Gdx;
import com.mob.client.Game;
import com.mob.dao.objects.*;
import com.mob.dao.loaders.*;

import java.util.Vector;

/**
 * Class AOAssetsReader
 *
 * This class handles all the logic for parsing
 * Argentum Online's data objects.
 */
public class AOAssetsReader implements AssetsReader {

    @Override
    public Map loadMap(String map) {
        Reader<Map> reader = new Reader<Map>();
        MapLoader loader = new MapLoader();

        return reader.read(Game.GAME_MAPS_PATH + "Mapa" + map + ".map", loader);
    }

    @Override
    public Vector<Graphic> loadGraphics() {
        Reader<Vector<Graphic>> reader = new Reader<Vector<Graphic>>();
        GraphicLoader loader = new GraphicLoader();

        return reader.read(Game.GAME_INIT_PATH + "graficos.ind", loader);
    }

    @Override
    public Vector<Body> loadBodies() {
        Reader<Vector<Body>> reader = new Reader<Vector<Body>>();
        BodyLoader loader = new BodyLoader();

        return reader.read(Game.GAME_INIT_PATH + "Personajes.ind", loader);
    }

    @Override
    public Vector<Fx> loadFxs() {
        Reader<Vector<Fx>> reader = new Reader<Vector<Fx>>();
        FxLoader loader = new FxLoader();

        return reader.read(Game.GAME_INIT_PATH + "Fxs.ind", loader);
    }

    @Override
    public Vector<Head> loadHeads() {
        Reader<Vector<Head>> reader = new Reader<Vector<Head>>();
        HeadLoader loader = new HeadLoader();

        return reader.read(Game.GAME_INIT_PATH + "Cabezas.ind", loader);
    }

    @Override
    public Vector<Helmet> loadHelmets() {
        Reader<Vector<Helmet>> reader = new Reader<Vector<Helmet>>();
        HelmetLoader loader = new HelmetLoader();

        return reader.read(Game.GAME_INIT_PATH + "Cascos.ind", loader);
    }
    @Override
    public Vector<Shield> loadShields() {
        Reader<Vector<Shield>> reader = new Reader<Vector<Shield>>();
        ShieldLoader loader = new ShieldLoader();

        return reader.read(Game.GAME_INIT_PATH + "Escudos.dat", loader);
    }

    @Override
    public Vector<Weapon> loadWeapons() {
        Reader<Vector<Weapon>> reader = new Reader<Vector<Weapon>>();
        WeaponLoader loader = new WeaponLoader();

        return reader.read(Game.GAME_INIT_PATH + "Armas.dat", loader);
    }

}
