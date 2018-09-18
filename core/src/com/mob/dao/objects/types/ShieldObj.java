package com.mob.dao.objects.types;

public class ShieldObj extends ObjWithClasses {

    private int bodyNumber;
    private int animationId;
    private int minDef, maxDef;

    public ShieldObj(String name, int grhIndex) {
        super(name, grhIndex);
    }

    @Override
    public Type getType() {
        return Type.SHIELD;
    }
}
