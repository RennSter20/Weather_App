package com.example.weather_app.retrofit.weather

import com.example.weather_app.retrofit.main.Main
import com.google.gson.annotations.SerializedName

class WeatherResponse {

    @SerializedName("weather")
    var weather: Weather

    constructor( weather: Weather) {
        this.weather = weather
    }

}