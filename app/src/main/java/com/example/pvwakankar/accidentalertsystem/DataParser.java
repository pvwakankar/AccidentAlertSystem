package com.example.pvwakankar.accidentalertsystem;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class DataParser {
    List<GooglePlace> parse(String jsonData) {
        JSONArray jsonArray = null;
        JSONObject jsonObject;

        try {
            Log.d("Places", "parse");
            jsonObject = new JSONObject((String) jsonData);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            Log.d("Places", "parse error");
            e.printStackTrace();
        }
        return getGooglePlaces(jsonArray);
    }

    private List<GooglePlace> getGooglePlaces(JSONArray jsonArray) {
        int placesCount = jsonArray.length();
        List<GooglePlace> placesList = new ArrayList<>();
        Log.d("Places", "getPlaces");

        for (int i = 0; i < placesCount; i++) {
            try {
                GooglePlace googlePlaces = getGooglePlace((JSONObject) jsonArray.get(i));
                placesList.add(googlePlaces);
                Log.d("Places", "Adding places");

            } catch (JSONException e) {
                Log.d("Places", "Error in Adding places");
                e.printStackTrace();
            }
        }
        return placesList;
    }
    private GooglePlace getGooglePlace(JSONObject googlePlaceJson) {
        Gson gson = new Gson();
        GooglePlace googlePlace = gson.fromJson(googlePlaceJson.toString(), GooglePlace.class);
        GooglePlace.Location location = null;
        try {
            location = gson.fromJson(googlePlaceJson.getJSONObject("geometry").getJSONObject("location").toString(), GooglePlace.Location.class);
        } catch (JSONException ignored) {
        }
        googlePlace.setLocation(location);
        return googlePlace;
    }
}
