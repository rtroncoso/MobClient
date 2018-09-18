package com.mob.dao.objects.types;

import com.esotericsoftware.minlog.Log;

public class PotionObj extends Obj {

    private Kind kind;
    private int min,max;
    private int effecTime;

    public PotionObj(String name, int grhIndex) {
        super(name, grhIndex);
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(int kind) {
        if (kind > Kind.values().length) {
            Log.error("Potion without valid kind");
            return;
        }
        this.kind = Kind.values()[kind - 1];
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getEffecTime() {
        return effecTime;
    }

    public void setEffecTime(int effecTime) {
        this.effecTime = effecTime;
    }

    @Override
    public Type getType() {
        return Type.POTION;
    }
}


// 1 Modifica la Agilidad
// 2 Modifica la Fuerza
// 3 Repone HP
// 4 Repone Mana
// 5 Cura Envenenamiento
enum Kind {
    AGILITY,
    STRENGTH,
    HP,
    MANA,
    POISON
}
