package com.example.pvwakankar.accidentalertsystem;

import java.util.ArrayList;

class GooglePlace {
    private String name;
    private String vicinity;
    private String reference;
    private Location location;
    private ArrayList types;

    GooglePlace(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public String getReference() {
        return reference;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setTypes(ArrayList types) {
        this.types = types;
    }

    public ArrayList getTypes() {
        return types;
    }

    public class Location {
        private double lat;
        private double lng;

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
    }
}