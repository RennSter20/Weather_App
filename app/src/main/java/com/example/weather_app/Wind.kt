package com.example.weather_app

class Wind {

    var speed:Long = 0
    var deg:Long = 0
    var gust:Long = 0

    constructor(speed: Long, deg: Long, gust: Long) {
        this.speed = speed
        this.deg = deg
        this.gust = gust
    }
}