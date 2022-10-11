package com.example.weather_app.info

class City {



    var cityName:String? = null
    var cityLon:String? = null
    var cityLat:String? = null

    var cityMainDescription:String? = null
    var cityDescription:String? = null
    var cityIcon:String? = null

    var temperature:Long? = null
    var feels_like:Long? = null
    var temp_min:Long? = null
    var temp_max:Long? = null
    var wind_speed:Long? = null

    var url:String? = null

    constructor(
        cityName: String?,
        cityLon: String?,
        cityLat: String?,
        cityMainDescription: String?,
        cityDescription: String?,
        cityIcon: String?,
        temperature: Long?,
        feels_like: Long?,
        temp_min: Long?,
        temp_max: Long?,
        wind_speed: Long?,
        url: String?
    ) {
        this.cityName = cityName
        this.cityLon = cityLon
        this.cityLat = cityLat
        this.cityMainDescription = cityMainDescription
        this.cityDescription = cityDescription
        this.cityIcon = cityIcon
        this.temperature = temperature
        this.feels_like = feels_like
        this.temp_min = temp_min
        this.temp_max = temp_max
        this.wind_speed = wind_speed
        this.url = url
    }






}