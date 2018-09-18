package com.mob.dao.descriptors;

public class BodyDescriptor extends Descriptor {

    public int headOffsetX;
    public int headOffsetY;

    public BodyDescriptor() {}
    public BodyDescriptor(int[] grhIndex, int headOffsetX, int headOffsetY) {
        super(grhIndex);
        this.headOffsetX = headOffsetX;
        this.headOffsetY = headOffsetY;
    }

    public int getHeadOffsetX() {
        return headOffsetX;
    }

    public int getHeadOffsetY() {
        return headOffsetY;
    }
}
