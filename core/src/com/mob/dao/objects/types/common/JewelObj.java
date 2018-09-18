package com.mob.dao.objects.types.common;

import com.mob.dao.objects.types.Obj;
import com.mob.dao.objects.types.Type;

public class JewelObj extends Obj {
    public JewelObj(String name, int grhIndex) {
        super(name, grhIndex);
    }

    @Override
    public Type getType() {
        return Type.JEWEL;
    }
}
