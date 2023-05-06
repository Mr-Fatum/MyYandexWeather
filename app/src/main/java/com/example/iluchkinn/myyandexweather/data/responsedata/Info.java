package com.example.iluchkinn.myyandexweather.data.responsedata;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("lat")
    @Expose
    public double lat;
    @SerializedName("lon")
    @Expose
    public double lon;

    @Override
    public String toString() {
        return (new Gson()).toJson(this);
    }
}