package com.mob.dao.objects.types;

public class ArmorObj extends ObjWithClasses {

    private int bodyNumber;
    private int minDef, maxDef;
    private boolean women;
    private boolean dwarf;

    public ArmorObj(String name, int grhIndex) {
        super(name, grhIndex);
    }

    @Override
    public Type getType() {
        return Type.ARMOR;
    }
}
