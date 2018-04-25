package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            return new Sandwich(new JSONObject(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> parseStringJSONArray(JSONArray jsonArray) {
        List<String> strings = new ArrayList<>();
        int size = jsonArray.length();
        for (int i=0; i<size; i++) {
            try {
                strings.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return strings;
    }

}
