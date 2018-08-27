package com.mob.client.items;

public class BodyItem extends DefenseItem {

    public BodyItem(int id, int value, int graphic) {
        super(id, value, graphic);
    }

    @Override
    public Kind getKind() {
        return Kind.BODY;
    }

}
