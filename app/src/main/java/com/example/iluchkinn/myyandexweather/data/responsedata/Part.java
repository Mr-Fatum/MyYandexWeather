package com.example.iluchkinn.myyandexweather.data.responsedata;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Part {

    @SerializedName("part_name")
    @Expose
    public String partName;
    @SerializedName("temp_min")
    @Expose
    public double tempMin;
    @SerializedName("temp_avg")
    @Expose
    public double tempAvg;
    @SerializedName("temp_max")
    @Expose
    public double tempMax;
    @SerializedName("wind_speed")
    @Expose
    public double windSpeed;
    @SerializedName("wind_gust")
    @Expose
    public double windGust;
    @SerializedName("wind_dir")
    @Expose
    public String windDir;
    @SerializedName("pressure_mm")
    @Expose
    public double pressureMm;
    @SerializedName("pressure_pa")
    @Expose
    public double pressurePa;
    @SerializedName("humidity")
    @Expose
    public double humidity;
    @SerializedName("prec_mm")
    @Expose
    public double precMm;
    @SerializedName("prec_prob")
    @Expose
    public double precProb;
    @SerializedName("prec_period")
    @Expose
    public double precPeriod;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("condition")
    @Expose
    public String condition;
    @SerializedName("feels_like")
    @Expose
    public double feelsLike;
    @SerializedName("daytime")
    @Expose
    public String daytime;
    @SerializedName("polar")
    @Expose
    public boolean polar;

    @Override
    public String toString() {
        return (new Gson()).toJson(this);
    }
}


