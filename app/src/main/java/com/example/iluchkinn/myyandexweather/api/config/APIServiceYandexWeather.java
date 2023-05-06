package com.example.iluchkinn.myyandexweather.api.config;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import com.example.iluchkinn.myyandexweather.data.responsedata.ResponseData;

public interface APIServiceYandexWeather {
    @GET("/v2/informers")
    @Headers("X-Yandex-API-Key:" + APIConfigYandexWeather.KEY)
    Call<ResponseData> getGetCityWeather(
            @Query("lat") double lat,
            @Query("lon") double lon);
}
