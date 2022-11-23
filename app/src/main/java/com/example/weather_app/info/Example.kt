package com.example.weather_app.info

import com.example.weather_app.retrofit.main.Main
import com.example.weather_app.retrofit.weather.Weather
import com.google.gson.annotations.SerializedName

class Example {

    @SerializedName("main")
    var main: Main

    @SerializedName("weather")
    var weather: Weather

    constructor(main: Main, weather: Weather) {
        this.main = main
        this.weather = weather
    }


}