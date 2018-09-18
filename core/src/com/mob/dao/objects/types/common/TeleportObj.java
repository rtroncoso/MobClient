package com.mob.dao.objects.types.common;

import com.mob.dao.objects.types.Obj;
import com.mob.dao.objects.types.Type;

public class TeleportObj extends Obj {
    public TeleportObj(String name, int grhIndex) {
        super(name, grhIndex);
    }

    @Override
    public Type getType() {
        return Type.TELEPORT;
    }
}
