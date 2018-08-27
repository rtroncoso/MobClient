package com.mob.dao.readers;

import com.badlogic.gdx.utils.LongMap;
import com.mob.dao.descriptors.*;
import com.mob.dao.objects.*;

public interface DescriptorsReader {

    Map loadMap(String map);

    LongMap<Graphic> loadGraphics();

    LongMap<FXDescriptor> loadFxs();

    LongMap<BodyDescriptor> loadBodies();

    LongMap<HeadDescriptor> loadHeads();

    LongMap<HelmetDescriptor> loadHelmets();

    LongMap<ShieldDescriptor> loadShields();

    LongMap<WeaponDescriptor> loadWeapons();
}
