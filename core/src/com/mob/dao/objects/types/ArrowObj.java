package com.mob.dao.objects.types;

public class ArrowObj extends ObjWithClasses {

    private int minHit, maxHit;

    public ArrowObj(String name, int grhIndex) {
        super(name, grhIndex);
    }

    public int getMinHit() {
        return minHit;
    }

    public void setMinHit(int minHit) {
        this.minHit = minHit;
    }

    public int getMaxHit() {
        return maxHit;
    }

    public void setMaxHit(int maxHit) {
        this.maxHit = maxHit;
    }

    @Override
    public Type getType() {
        return Type.ARROW;
    }
}
