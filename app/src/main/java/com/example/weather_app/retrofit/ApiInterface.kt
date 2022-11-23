package com.example.weather_app.retrofit

import com.example.weather_app.retrofit.weather.WeatherResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("weather?appid=a0c9131636ddbd513b2be78ca26b3a24&units=metric")
    fun getWeatherData(@Query("q") name: String?): Call<Any?>



}