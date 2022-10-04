package com.example.weather_app

class Weather {

    var id:Int = 0
    var main:String = ""
    var description:String = ""
    var icon:String = ""

    constructor(id: Int, main: String, description: String, icon: String) {
        this.id = id
        this.main = main
        this.description = description
        this.icon = icon
    }
}