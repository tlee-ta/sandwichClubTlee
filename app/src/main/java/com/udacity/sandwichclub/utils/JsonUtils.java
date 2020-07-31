package com.udacity.sandwichclub.utils;

import android.media.Image;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        // Initialize all variables
        Sandwich thisSandwich = new Sandwich();
        String sMainName = "";
        List<String> sAlsoKnownAs = new ArrayList<>();
        String sPlaceOfOrigin = "";
        String sDescription = "";
        String sImage = "";
        List<String> sIngredients = new ArrayList<>();

        try {
            // Retrieve the json we want to parse through
            JSONObject sandwichDetails = new JSONObject(json);
            JSONObject sandwichName = sandwichDetails.getJSONObject("name");

            // Set the strings for all fields that are not arrays
            sMainName = sandwichName.getString("mainName");
            sPlaceOfOrigin =  sandwichDetails.getString("placeOfOrigin");
            sDescription =  sandwichDetails.getString("description");
            sImage = sandwichDetails.getString("image");

            // Iterate through and add each item in the array to the string list
            JSONArray jAlsoKnownAs = sandwichName.getJSONArray("alsoKnownAs");
            for ( int i = 0; i < jAlsoKnownAs.length(); i++) {
                String strAlsoKnownAs = jAlsoKnownAs.getString(i);
                sAlsoKnownAs.add(strAlsoKnownAs);
            }

            // Iterate through and add each item in the array to the string list
            JSONArray jIngredients =  sandwichDetails.getJSONArray("ingredients");
            for ( int i = 0; i < jIngredients.length(); i++) {
                String strIngredients = jIngredients.getString(i);
                sIngredients.add(strIngredients);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        // Set all the sandwich properties that we retrieved from the json
        thisSandwich.setMainName(sMainName);
        thisSandwich.setAlsoKnownAs(sAlsoKnownAs);
        thisSandwich.setPlaceOfOrigin(sPlaceOfOrigin);
        thisSandwich.setDescription(sDescription);
        thisSandwich.setImage(sImage);
        thisSandwich.setIngredients(sIngredients);

        return thisSandwich;
    }
}
