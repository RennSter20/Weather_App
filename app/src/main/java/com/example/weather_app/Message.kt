package com.example.weather_app

class Message {

    var coord:Coordinates? = null
    var weather:Weather? = null
    var base:String? = null
    var main:Main? = null
    var visibility:Long? = null
    var wind:Wind? = null
    var rain:Rain? = null
    var clouds:Clouds? = null
    var dt:Long? = null
    var sys:Sys? = null
    var timezone:Long? = null
    var id:Long? = null
    var nameS:String? = null
    var cod:Long? = null

    constructor(
        coord: Coordinates?,
        weather: Weather?,
        base: String?,
        main: Main?,
        visibility: Long?,
        wind: Wind?,
        rain: Rain?,
        clouds: Clouds?,
        dt: Long?,
        sys: Sys?,
        timezone: Long?,
        id: Long?,
        nameS: String?,
        cod: Long?
    ) {
        this.coord = coord
        this.weather = weather
        this.base = base
        this.main = main
        this.visibility = visibility
        this.wind = wind
        this.rain = rain
        this.clouds = clouds
        this.dt = dt
        this.sys = sys
        this.timezone = timezone
        this.id = id
        this.nameS = nameS
        this.cod = cod
    }
}