package com.mob.dao.objects.types;

public class KeyObj extends Obj {

    private int key;

    public KeyObj(String name, int grhIndex) {
        super(name, grhIndex);
    }

    @Override
    public Type getType() {
        return Type.KEYS;
    }
}
