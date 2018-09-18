package com.mob.dao.objects.types.common;

import com.mob.dao.objects.types.Obj;
import com.mob.dao.objects.types.Type;

public class GoldObj extends Obj {
    public GoldObj(String name, int grhIndex) {
        super(name, grhIndex);
    }

    @Override
    public Type getType() {
        return Type.GOLD;
    }
}
