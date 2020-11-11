package com.udacity.sandwichclub.utils;


import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static Sandwich parseSandwichJson(String jsonstring) {

    Sandwich sandwich;
    JSONObject name;
    String mainName = null;
    List<String> alsoKnownAs = null;
    String placeOfOrigin = null;
    String description = null;
    String image = null;
    List<String> ingredients = null;

        try {
        JSONObject x = new JSONObject(jsonstring);
        name = x.getJSONObject("name");
        mainName = name.getString("mainName");

        JSONArray alsoKnownAsString = name.getJSONArray("alsoKnownAs");
        alsoKnownAs = new ArrayList<>();

        if (alsoKnownAsString.length() != 0){
            for (int i = 0; i < alsoKnownAsString.length(); i++) {
                alsoKnownAs.add(alsoKnownAsString.getString(i));
            }
        }

        placeOfOrigin = x.getString("placeOfOrigin");
        description = x.getString("description");
        image = x.getString("image");

        JSONArray ingredientsArray = x.getJSONArray("ingredients");
        ingredients = new ArrayList<>();

        if (ingredientsArray.length() != 0) {
            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredients.add(ingredientsArray.getString(i));
            }
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }

    sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        return sandwich;
}
}
