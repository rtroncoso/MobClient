package com.mob.dao.objects.types;

public class HelmetObj extends ObjWithClasses {

    private int bodyNumber;
    private int animationId;
    private int minDef, maxDef;

    public HelmetObj(String name, int grhIndex) {
        super(name, grhIndex);
    }

    @Override
    public Type getType() {
        return Type.HELMET;
    }
}
