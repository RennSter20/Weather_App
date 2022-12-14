package com.example.weather_app.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("weather?appid=a0c9131636ddbd513b2be78ca26b3a24&units=metric")
    fun getWeatherData(@Query("q") name: String?): Call<Object>

    @GET("weather?appid=a0c9131636ddbd513b2be78ca26b3a24&units=metric")
    fun getWeatherDataWithCo(@Query("lat") lat: String?, @Query("lon")lon: String?): Call<Object>

}