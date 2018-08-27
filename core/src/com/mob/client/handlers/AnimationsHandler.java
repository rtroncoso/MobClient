package com.mob.client.handlers;

import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.LongMap;
import com.mob.client.textures.BundledAnimation;
import com.mob.dao.descriptors.*;
import com.mob.dao.objects.Graphic;

import java.util.HashMap;
import java.util.Map;

public class AnimationsHandler {
    private static Map<Integer, IntMap<BundledAnimation>> bodyAnimations;
    private static Map<Integer, IntMap<BundledAnimation>> headAnimations;
    private static Map<Integer, IntMap<BundledAnimation>> helmetAnimations;
    private static Map<Integer, IntMap<BundledAnimation>> weaponAnimations;
    private static Map<Integer, IntMap<BundledAnimation>> shieldAnimations;
    private static Map<Integer, IntMap<BundledAnimation>> fxAnimations;

    public static void load() {
        bodyAnimations = loadDescriptors(DescriptorsHandler.getBodies());
        headAnimations = loadDescriptors(DescriptorsHandler.getHeads());
        helmetAnimations = loadDescriptors(DescriptorsHandler.getHelmets());
        weaponAnimations = loadDescriptors(DescriptorsHandler.getWeapons());
        shieldAnimations = loadDescriptors(DescriptorsHandler.getShields());
        fxAnimations = loadDescriptors(DescriptorsHandler.getFxs());
    }

    private static Map<Integer, IntMap<BundledAnimation>> loadDescriptors(LongMap<?> descriptors) {
        Map<Integer, IntMap<BundledAnimation>> result = new HashMap<>();
        descriptors.forEach(descriptorEntry -> {
            result.put((int) descriptorEntry.key, createAnimations((IDescriptor) descriptorEntry.value));
        });
        return result;
    }

    private static IntMap<BundledAnimation> createAnimations(IDescriptor descriptor) {
        IntMap<BundledAnimation> animations = new IntMap<>();
        int[] indexs = descriptor.getIndexs();
        for (int i = 0; i < indexs.length; i++) {
            Graphic grh = DescriptorsHandler.getGraphic(indexs[i]);
            if (grh != null) {
                animations.put(i, new BundledAnimation(DescriptorsHandler.getGraphic(indexs[i])));
            }
        }
        return animations;
    }

    public static BundledAnimation getHeadAnimation(int index, int current) {
        return headAnimations.get(index).get(current);
    }

    public static BundledAnimation getBodyAnimation(int index, int current) {
        return bodyAnimations.get(index).get(current);
    }

    public static BundledAnimation getWeaponAnimation(int index, int current) {
        return weaponAnimations.get(index).get(current);
    }

    public static BundledAnimation getHelmetsAnimation(int index, int current) {
        return helmetAnimations.get(index).get(current);
    }

    public static BundledAnimation getShieldAnimation(int index, int current) {
        return shieldAnimations.get(index).get(current);
    }

    public static BundledAnimation getFXAnimation(int index, int current) {
        return fxAnimations.get(index).get(current);
    }
}