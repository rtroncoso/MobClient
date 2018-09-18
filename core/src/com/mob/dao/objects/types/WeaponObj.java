package com.mob.dao.objects.types;

public class WeaponObj extends ObjWithClasses {

    private int animationId;
    private int dwarfAnimationId;
    private int minHit, maxHit;

    public WeaponObj(String name, int grhIndex) {
        super(name, grhIndex);
    }

    @Override
    public Type getType() {
        return Type.WEAPON;
    }
}
