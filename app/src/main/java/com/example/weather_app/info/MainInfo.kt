package com.example.weather_app.info

class MainInfo {

    var temperature:Long? = null
    var feels_like:Long? = null
    var temp_min:Long? = null
    var temp_max:Long? = null
    var pressure:Long? = null

    constructor(
        temperature: Long?,
        feels_like: Long?,
        temp_min: Long?,
        temp_max: Long?,
        pressure: Long?
    ) {
        this.temperature = temperature
        this.feels_like = feels_like
        this.temp_min = temp_min
        this.temp_max = temp_max
        this.pressure = pressure
    }
}