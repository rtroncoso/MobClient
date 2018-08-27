package com.mob.client.items;

public class PotionItem extends Item {

    public PotionItem(int id, int value, int graphic) {
        super(id, value, graphic);
    }

    @Override
    public Kind getKind() {
        return Kind.POTION;
    }
}
