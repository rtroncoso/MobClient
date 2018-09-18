package com.mob.dao.objects.types;

public class Food extends Obj {
    private int min;

    public Food(String name, int grhIndex) {
        super(name, grhIndex);
    }

    @Override
    public Type getType() {
        return Type.FOOD;
    }
}
