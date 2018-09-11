package com.mob.client.handlers;

import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.LongMap;
import com.mob.client.textures.BundledAnimation;
import com.mob.dao.descriptors.*;
import com.mob.dao.objects.Graphic;

import java.util.*;

public class AnimationsHandler {
    private static Map<Integer, List<BundledAnimation>> bodyAnimations;
    private static Map<Integer, List<BundledAnimation>> headAnimations;
    private static Map<Integer, List<BundledAnimation>> helmetAnimations;
    private static Map<Integer, List<BundledAnimation>> weaponAnimations;
    private static Map<Integer, List<BundledAnimation>> shieldAnimations;
    private static Map<Integer, List<BundledAnimation>> fxAnimations;

    public static void load() {
        bodyAnimations = loadDescriptors(DescriptorsHandler.getBodies());
        headAnimations = loadDescriptors(DescriptorsHandler.getHeads());
        helmetAnimations = loadDescriptors(DescriptorsHandler.getHelmets());
        weaponAnimations = loadDescriptors(DescriptorsHandler.getWeapons());
        shieldAnimations = loadDescriptors(DescriptorsHandler.getShields());
        fxAnimations = loadDescriptors(DescriptorsHandler.getFxs());
    }

    private static Map<Integer, List<BundledAnimation>> loadDescriptors(List<?> descriptors) {
        Map<Integer, List<BundledAnimation>> result = new HashMap<>();
        int[] idx = { 1 };
        descriptors.forEach(descriptor -> {
            result.put(idx[0]++, createAnimations((IDescriptor) descriptor));
        });
        return result;
    }

    private static Map<Integer, List<BundledAnimation>> loadDescriptors(Map<Integer, ?> descriptors) {
        Map<Integer, List<BundledAnimation>> result = new HashMap<>();
        descriptors.forEach((id, descriptor) -> {
            result.put(id, createAnimations((IDescriptor) descriptor));
        });
        return result;
    }

    private static List<BundledAnimation> createAnimations(IDescriptor descriptor) {
        List<BundledAnimation> animations = new ArrayList<>();
        int[] indexs = descriptor.getIndexs();
        for (int i = 0; i < indexs.length; i++) {
            Graphic grh = DescriptorsHandler.getGraphic(indexs[i]);
            if (grh != null) {
                animations.add(new BundledAnimation(DescriptorsHandler.getGraphic(indexs[i])));
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