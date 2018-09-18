package com.mob.dao.objects.types;

public class MagicObj extends ObjWithClasses{
    private int minHit, maxHit;
    private int minDef, maxDef;

    public MagicObj(String name, int grhIndex) {
        super(name, grhIndex);
    }

    @Override
    public Type getType() {
        return Type.RING;
    }
}
