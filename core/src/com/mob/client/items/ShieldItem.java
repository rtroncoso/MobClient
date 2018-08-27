package com.mob.client.items;

public class ShieldItem extends DefenseItem {

    public ShieldItem(int id, int value, int graphic) {
        super(id, value, graphic);
    }

    @Override
    public Kind getKind() {
        return Kind.SHIELD;
    }

}
