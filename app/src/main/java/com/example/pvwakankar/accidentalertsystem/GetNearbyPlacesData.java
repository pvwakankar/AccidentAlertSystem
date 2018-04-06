package com.example.pvwakankar.accidentalertsystem;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;

public class GetNearbyPlacesData extends AsyncTask<Object, String, String> {
    String googlePlacesData;
    GoogleMap mMap;
    String url;

    @Override
    protected String doInBackground(Object... params) {
        try {
            Log.d("GetNearbyPlacesData", "doInBackground entered");
            mMap = (GoogleMap) params[0];
            url = (String) params[1];
            DownloadUrl downloadUrl = new DownloadUrl();
            googlePlacesData = downloadUrl.readUrl(url);
            Log.d("GooglePlacesReadTask", "doInBackground Exit");
        } catch (Exception e) {
            Log.d("GooglePlacesReadTask", e.toString());
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("GooglePlacesReadTask", "onPostExecute Entered");
        DataParser dataParser = new DataParser();
        List<GooglePlace> nearbyPlacesList = dataParser.parse(result);
        ShowNearbyPlaces(nearbyPlacesList);
        Log.d("GooglePlacesReadTask", "onPostExecute Exit");
    }

    private void ShowNearbyPlaces(List<GooglePlace> nearbyPlacesList) {
        Log.d("onPostExecute", "Entered into showing locations");

        for (GooglePlace googlePlace : nearbyPlacesList) {
            LatLng latLng = new LatLng(googlePlace.getLocation().getLat(), googlePlace.getLocation().getLng());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            setMarkerColor(googlePlace, markerOptions);
            markerOptions.title(googlePlace.getName() + " : " + googlePlace.getVicinity());
            mMap.addMarker(markerOptions);
            //move map camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    private void setMarkerColor(GooglePlace googlePlace, MarkerOptions markerOptions) {
        if (googlePlace.getTypes().contains("hospital")) {
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        } else if (googlePlace.getTypes().contains("car_repair")) {
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        } else if (googlePlace.getTypes().contains("doctor")) {
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        } else if (googlePlace.getTypes().contains("police")) {
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        }
    }
}
