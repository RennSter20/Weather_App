package com.example.weather_app

class Sys {

    var type:Int = 0
    var id:Long = 0
    var country:String = ""
    var sunrise:Long = 0
    var sunset:Long = 0

    constructor(type: Int, id: Long, country: String, sunrise: Long, sunset: Long) {
        this.type = type
        this.id = id
        this.country = country
        this.sunrise = sunrise
        this.sunset = sunset
    }
}