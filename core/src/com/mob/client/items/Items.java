package com.mob.client.items;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Items {

    private static final Set<Kind> EQUIPABLE_KINDS;

    static {
        EQUIPABLE_KINDS = new HashSet<>();
        EQUIPABLE_KINDS.add(Kind.BODY);
        EQUIPABLE_KINDS.add(Kind.SHIELD);
        EQUIPABLE_KINDS.add(Kind.WEAPON);
        EQUIPABLE_KINDS.add(Kind.HELMET);
    }

    private static List<BodyItem> bodyItems;

    private static List<BodyItem> getBodies() {
        List<BodyItem> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BodyItem bodyItem = new BodyItem(i, 100, i);
            bodyItem.setDefenseMin(10);
            bodyItem.setDefenseMax(20);
            result.add(bodyItem);
        }
        return result;
    }

}
