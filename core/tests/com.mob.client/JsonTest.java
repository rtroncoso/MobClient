package com.mob.client;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.minlog.Log;

import java.util.ArrayList;

public class JsonTest {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("pepe");
        list.add(null);
        list.add("pepe");

        Json json = new Json();
        json.setDefaultSerializer(new Json.Serializer<ArrayList<String>>() {

            @Override
            public void write(Json json, ArrayList<String> object, Class knownType) {

            }

            @Override
            public ArrayList<String> read(Json json, JsonValue jsonData, Class type) {
                ArrayList<String> list = new ArrayList<>();
                jsonData.forEach(entry -> {

                });
                return list;
            }
        });

        Log.info(json.toJson(list));
    }
}
