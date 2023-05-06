package com.example.iluchkinn.myyandexweather.data.responsedata;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("now")
    @Expose
    public int now;
    @SerializedName("now_dt")
    @Expose
    public String nowDt;
    @SerializedName("info")
    @Expose
    public Info info;
    @SerializedName("fact")
    @Expose
    public Fact fact;
    @SerializedName("forecast")
    @Expose
    public Forecast forecast;

    @Override
    public String toString() {
        return (new Gson()).toJson(this);
    }
}
