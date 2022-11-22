package com.example.weather_app.info

import com.google.gson.annotations.SerializedName

class Example {

    @SerializedName("main")
    var main:Main

    constructor(main: Main) {
        this.main = main
    }
}