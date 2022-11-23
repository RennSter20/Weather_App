package com.example.weather_app.retrofit.main

import com.google.gson.annotations.SerializedName

class Main {


    var temperature:String
    var feels_like:String

    constructor(temperature: String, feels_like: String) {
        this.temperature = temperature
        this.feels_like = feels_like
    }
}