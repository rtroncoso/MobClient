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

import com.badlogic.gdx.utils.LongMap;
import com.mob.client.Game;
import com.mob.dao.objects.*;
import com.mob.dao.loaders.*;

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
    public LongMap<Graphic> loadGraphics() {
        Reader<LongMap<Graphic>> reader = new Reader<LongMap<Graphic>>();
        GraphicLoader loader = new GraphicLoader();

        return reader.read(Game.GAME_INIT_PATH + "Graficos.ind", loader);
    }

    @Override
    public LongMap<Body> loadBodies() {
        Reader<LongMap<Body>> reader = new Reader<LongMap<Body>>();
        BodyLoader loader = new BodyLoader();

        return reader.read(Game.GAME_INIT_PATH + "Personajes.ind", loader);
    }

    @Override
    public LongMap<Fx> loadFxs() {
        Reader<LongMap<Fx>> reader = new Reader<LongMap<Fx>>();
        FxLoader loader = new FxLoader();

        return reader.read(Game.GAME_INIT_PATH + "Fxs.ind", loader);
    }

    @Override
    public LongMap<Head> loadHeads() {
        Reader<LongMap<Head>> reader = new Reader<LongMap<Head>>();
        HeadLoader loader = new HeadLoader();

        return reader.read(Game.GAME_INIT_PATH + "Cabezas.ind", loader);
    }

    @Override
    public LongMap<Helmet> loadHelmets() {
        Reader<LongMap<Helmet>> reader = new Reader<LongMap<Helmet>>();
        HelmetLoader loader = new HelmetLoader();

        return reader.read(Game.GAME_INIT_PATH + "Cascos.ind", loader);
    }
    @Override
    public LongMap<Shield> loadShields() {
        Reader<LongMap<Shield>> reader = new Reader<LongMap<Shield>>();
        ShieldLoader loader = new ShieldLoader();

        return reader.read(Game.GAME_INIT_PATH + "Escudos.dat", loader);
    }

    @Override
    public LongMap<Weapon> loadWeapons() {
        Reader<LongMap<Weapon>> reader = new Reader<LongMap<Weapon>>();
        WeaponLoader loader = new WeaponLoader();

        return reader.read(Game.GAME_INIT_PATH + "Armas.dat", loader);
    }

}
