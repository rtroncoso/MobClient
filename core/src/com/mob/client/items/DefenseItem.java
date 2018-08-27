package com.mob.client.items;

public abstract class DefenseItem extends Item {
    private int defenseMin;
    private int defenseMax;

    public DefenseItem(int id, int value, int graphic) {
        super(id, value, graphic);
    }

    public int getDefenseMax() {
        return defenseMax;
    }

    public void setDefenseMax(int defenseMax) {
        this.defenseMax = defenseMax;
    }

    public int getDefenseMin() {
        return defenseMin;
    }

    public void setDefenseMin(int defenseMin) {
        this.defenseMin = defenseMin;
    }
}
