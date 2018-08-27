package com.mob.client.items;

public abstract class AttackItem extends Item {
    private int attackMin;
    private int attackMax;

    public AttackItem(int id, int value, int graphic) {
        super(id, value, graphic);
    }

    public int getAttackMin() {
        return attackMin;
    }

    public int getAttackMax() {
        return attackMax;
    }

    public void setAttackMax(int attackMax) {
        this.attackMax = attackMax;
    }

    public void setAttackMin(int attackMin) {
        this.attackMin = attackMin;
    }

}
