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

}
