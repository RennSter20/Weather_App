package com.example.weather_app.info

class City {



    var cityName:String? = null
    var cityLon:String? = null
    var cityLat:String? = null

    var cityMainDescription:String? = null
    var cityDescription:String? = null
    var cityIcon:String? = null

    var temperature:String? = null
    var feels_like:String? = null
    var temp_min:String? = null
    var temp_max:String? = null

    constructor(
        cityName: String?,//
        cityLon: String?,
        cityLat: String?,
        cityMainDescription: String?,//
        cityDescription: String?,
        cityIcon: String?,//
        temperature: String?,//
        feels_like: String?,
        temp_min: String?,
        temp_max: String?,
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
    }






}