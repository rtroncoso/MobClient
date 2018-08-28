package com.mob.client.items;

import java.util.HashSet;
import java.util.Set;

public abstract class Item {

    private int id;
    private int value;
    private int graphic;
    private static final Set<Kind> EQUIPABLE_KINDS;
    public abstract Kind getKind();

    public Item() {}
    public Item(int id, int value, int graphic) {
        this.id = id;
        this.value = value;
        this.graphic = graphic;
    }

    static {
        EQUIPABLE_KINDS = new HashSet<>();
        EQUIPABLE_KINDS.add(Kind.BODY);
        EQUIPABLE_KINDS.add(Kind.SHIELD);
        EQUIPABLE_KINDS.add(Kind.WEAPON);
        EQUIPABLE_KINDS.add(Kind.HELMET);
    }

    public boolean isEquipable() {
        return EQUIPABLE_KINDS.contains(getKind());
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public int getGraphic() {
        return graphic;
    }

}
