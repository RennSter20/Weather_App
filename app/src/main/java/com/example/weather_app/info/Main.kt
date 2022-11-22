package com.example.weather_app.info

import com.google.gson.annotations.SerializedName

class Main {

    @SerializedName("temperature")
    var temperature:Double
    @SerializedName("feels_like")
    var feels_like:Double

    constructor(temperature: Double, feels_like: Double) {
        this.temperature = temperature
        this.feels_like = feels_like
    }
}