package com.mob.dao.objects.types.common;

import com.mob.dao.objects.types.Obj;
import com.mob.dao.objects.types.Type;

public class AnvilObj extends Obj {
    public AnvilObj(String name, int grhIndex) {
        super(name, grhIndex);
    }

    @Override
    public Type getType() {
        return Type.ANVIL;
    }
}
