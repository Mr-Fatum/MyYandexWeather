package com.example.iluchkinn.myyandexweather.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.iluchkinn.myyandexweather.R;
import com.example.iluchkinn.myyandexweather.api.config.APIServiceYandexWeather;
import com.example.iluchkinn.myyandexweather.data.City;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.iluchkinn.myyandexweather.api.APIServiceConstructor;
import com.example.iluchkinn.myyandexweather.api.config.APIConfigYandexWeather;
import com.example.iluchkinn.myyandexweather.data.responsedata.ResponseData;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class Main extends AppCompatActivity {
    private TextView textView, textView1, textView2, textView3, textView4, textView5, textView6;
    private SwipeRefreshLayout refreshLayout;
    private APIServiceYandexWeather service;
    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createService();
        initView();
        loadData();
    }

    private void initView() {
        textView = findViewById(R.id.toDay);
        textView1 = findViewById(R.id.temp);
        textView2 = findViewById(R.id.humidity);
        textView3 = findViewById(R.id.condition);
        textView4 = findViewById(R.id.moon_code);
        textView5 = findViewById(R.id.pressure);
        textView6 = findViewById(R.id.wind);
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this::loadData);
    }

    private void createService() {
        service = APIServiceConstructor.CreateService(
                APIServiceYandexWeather.class,
                APIConfigYandexWeather.HOST_URL);
        city = new City(56.50, 60.35);     //Ekb
        //city = new City( 55.74, 37.62);     //Msc
    }

    private void loadData() {
        refreshLayout.setRefreshing(true);
        AsyncTask.execute(() -> {
            Call<ResponseData> call_get = service.getGetCityWeather(
                    city.getLat(), city.getLon()
            );
            call_get.enqueue(new Callback<ResponseData>() {
                @Override
                public void onResponse(
                        @NonNull Call<ResponseData> call,
                        @NonNull Response<ResponseData> response
                ) {
                    if (response.body() != null) {
                        String text = (new Gson()).toJson(response.body());
                        Object document = Configuration.defaultConfiguration().jsonProvider().parse(text);
                        String date = JsonPath.read(document, "$.forecast.date");
                        String condition =  JsonPath.read(document, "$.fact.condition");
                        int feelsLike = JsonPath.read(document, "$.fact.feels_like");
                        String windDir = JsonPath.read(document, "$.fact.wind_dir");
                        double windSpeed = JsonPath.read(document, "$.fact.wind_speed");
                        int temp = JsonPath.read(document, "$.fact.temp");
                        int humidity = JsonPath.read(document, "$.fact.humidity");
                        int pressureMm = JsonPath.read(document, "$.fact.pressure_mm");
                        int moonCode = JsonPath.read(document, "$.forecast.moon_code");

                        textView.setText("Today is: " + date);
                        textView1.setText("Today temperature is: " + temp + "C°" + "\nTemperature feels like: " + feelsLike + "C°");
                        textView2.setText("Today humidity is: " + humidity + "%");
                        textView3.setText("Condition is: " + condition);
                        textView4.setText("Today moon code is: " + moonCode);
                        textView5.setText("Today pressure is: " + pressureMm + "mm");
                        textView6.setText("Wind speed is: " + windSpeed + "m/s" + "\nWind direction is: " + windDir);

                        Toast.makeText(
                                getApplicationContext(),
                                text,
                                Toast.LENGTH_LONG
                        ).show();
                        Log.d("ResponseData", text);
                    }
                    refreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(
                        @NonNull Call<ResponseData> call,
                        @NonNull Throwable t
                ) {
                    textView.setText(t.toString());
                    Toast.makeText(
                            getApplicationContext(),
                            t.toString(),
                            Toast.LENGTH_LONG
                    ).show();
                    Log.d("ResponseData", t.toString());
                    refreshLayout.setRefreshing(false);
                }
            });
        });
    }
}