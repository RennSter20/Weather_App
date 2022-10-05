package com.example.weather_app.info

class Weather {

    var main:String? = null
    var description:String? = null

    constructor(main: String?, description: String?) {
        this.main = main
        this.description = description
    }
}