package com.mob.client.items;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

    public static void main(String[] args) {
        List<BodyItem> bodies = getBodies();
        Type listType = new TypeToken<ArrayList<BodyItem>>(){}.getType();
        List<BodyItem> bodyItems = new Gson().fromJson("[{\"defenseMin\":10,\"defenseMax\":20,\"id\":0,\"value\":100,\"graphic\":0},{\"defenseMin\":10,\"defenseMax\":20,\"id\":1,\"value\":100,\"graphic\":1},{\"defenseMin\":10,\"defenseMax\":20,\"id\":2,\"value\":100,\"graphic\":2},{\"defenseMin\":10,\"defenseMax\":20,\"id\":3,\"value\":100,\"graphic\":3},{\"defenseMin\":10,\"defenseMax\":20,\"id\":4,\"value\":100,\"graphic\":4},{\"defenseMin\":10,\"defenseMax\":20,\"id\":5,\"value\":100,\"graphic\":5},{\"defenseMin\":10,\"defenseMax\":20,\"id\":6,\"value\":100,\"graphic\":6},{\"defenseMin\":10,\"defenseMax\":20,\"id\":7,\"value\":100,\"graphic\":7},{\"defenseMin\":10,\"defenseMax\":20,\"id\":8,\"value\":100,\"graphic\":8},{\"defenseMin\":10,\"defenseMax\":20,\"id\":9,\"value\":100,\"graphic\":9}]", listType);
        System.out.print(bodyItems);
    }

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
