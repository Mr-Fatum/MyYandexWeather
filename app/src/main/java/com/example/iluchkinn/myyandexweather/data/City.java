package com.example.iluchkinn.myyandexweather.data;

public class City {
    private final double lat;
    private final double lon;

    public City(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
