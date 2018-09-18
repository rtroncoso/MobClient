package com.mob.dao.objects.types;

public class MineralObj extends Obj{
    private int ingotIndex;

    public MineralObj(String name, int grhIndex) {
        super(name, grhIndex);
    }

    @Override
    public Type getType() {
        return Type.METAL;
    }
}
