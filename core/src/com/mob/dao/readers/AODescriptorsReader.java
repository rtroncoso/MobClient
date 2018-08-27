package com.mob.dao.readers;

import com.badlogic.gdx.utils.LongMap;
import com.mob.client.Game;
import com.mob.dao.descriptors.*;
import com.mob.dao.loaders.*;
import com.mob.dao.objects.*;

public class AODescriptorsReader implements DescriptorsReader {
    @Override
    public Map loadMap(String map) {
        Reader<Map> reader = new Reader<Map>();
        MapLoader loader = new MapLoader();
        return reader.read(Game.GAME_MAPS_PATH + "Mapa" + map + ".map", loader);
    }

    @Override
    public LongMap<Graphic> loadGraphics() {
        Reader<LongMap<Graphic>> reader = new Reader<>();
        GraphicLoader loader = new GraphicLoader();
        return reader.read(Game.GAME_INIT_PATH + "Graficos.ind", loader);
    }

    @Override
    public LongMap<FXDescriptor> loadFxs() {
        Reader<LongMap<FXDescriptor>> reader = new Reader<>();
        FxLoader loader = new FxLoader();
        return reader.read(Game.GAME_INIT_PATH + "Fxs.ind", loader);
    }

    @Override
    public LongMap<BodyDescriptor> loadBodies() {
        Reader<LongMap<BodyDescriptor>> reader = new Reader<>();
        BodyLoader loader = new BodyLoader();
        return reader.read(Game.GAME_INIT_PATH + "Personajes.ind", loader);
    }

    @Override
    public LongMap<HeadDescriptor> loadHeads() {
        Reader<LongMap<HeadDescriptor>> reader = new Reader<>();
        HeadLoader loader = new HeadLoader();

        return reader.read(Game.GAME_INIT_PATH + "Cabezas.ind", loader);
    }

    @Override
    public LongMap<HelmetDescriptor> loadHelmets() {
        Reader<LongMap<HelmetDescriptor>> reader = new Reader<>();
        HelmetLoader loader = new HelmetLoader();
        return reader.read(Game.GAME_INIT_PATH + "Cascos.ind", loader);
    }

    @Override
    public LongMap<ShieldDescriptor> loadShields() {
        Reader<LongMap<ShieldDescriptor>> reader = new Reader<>();
        ShieldLoader loader = new ShieldLoader();
        return reader.read(Game.GAME_INIT_PATH + "Escudos.dat", loader);
    }

    @Override
    public LongMap<WeaponDescriptor> loadWeapons() {
        Reader<LongMap<WeaponDescriptor>> reader = new Reader<>();
        WeaponLoader loader = new WeaponLoader();
        return reader.read(Game.GAME_INIT_PATH + "Armas.dat", loader);
    }
}
