package com.mob.dao.objects.types;

public class DepositObj extends Obj{
    private int mineralIndex;

    public DepositObj(String name, int grhIndex) {
        super(name, grhIndex);
    }

    @Override
    public Type getType() {
        return Type.DEPOSIT;
    }
}
