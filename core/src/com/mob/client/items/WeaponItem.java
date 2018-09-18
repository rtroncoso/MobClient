package com.mob.client.items;

public class WeaponItem extends AttackItem {

    public WeaponItem(int id, int value, int graphic) {
        super(id, value, graphic);
    }

    @Override
    public Kind getKind() {
        return Kind.WEAPON;
    }

}
