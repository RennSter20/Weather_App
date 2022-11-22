package com.example.weather_app.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? { //creating object
        if (retrofit == null) {
            retrofit =
                Retrofit.Builder() //Retrofit.Builder class uses the Builder API to allow defining the URL end point for the HTTP operations and finally build a new Retrofit instance.
                    //http://api.openweathermap.org/data/2.5/weather?q=London&APPID=76a35a17f3e1bce771a09f3555b614a8
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }

}