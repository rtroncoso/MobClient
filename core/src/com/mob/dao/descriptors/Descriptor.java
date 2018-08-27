package com.mob.dao.descriptors;

public class Descriptor implements IDescriptor{

    public Descriptor(){}

    protected int[] indexs;

    public Descriptor(int[] grhIndex) { this.indexs = grhIndex; }

    public int[] getIndexs() { return indexs; }

    public int getGraphic(int index) {
        return this.indexs[index];
    }
}
