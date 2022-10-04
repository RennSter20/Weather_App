package com.example.weather_app

class Main {

    var temp:Long = 0
    var feels_like:Long = 0
    var temp_min:Long = 0
    var temp_max:Long = 0
    var pressure:Long = 0
    var humidity:Int =0
    var sea_level:Long = 0
    var grnd_level:Long = 0

    constructor(
        temp: Long,
        feels_like: Long,
        temp_min: Long,
        temp_max: Long,
        pressure: Long,
        humidity: Int,
        sea_level: Long,
        grnd_level: Long
    ) {
        this.temp = temp
        this.feels_like = feels_like
        this.temp_min = temp_min
        this.temp_max = temp_max
        this.pressure = pressure
        this.humidity = humidity
        this.sea_level = sea_level
        this.grnd_level = grnd_level
    }
}