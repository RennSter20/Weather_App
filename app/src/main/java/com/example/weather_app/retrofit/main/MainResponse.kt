package com.example.weather_app.retrofit.main

import com.example.weather_app.retrofit.weather.Weather
import com.google.gson.annotations.SerializedName

class MainResponse {

    @SerializedName("main")
    var main: Main

    constructor(main: Main) {
        this.main = main
    }

}