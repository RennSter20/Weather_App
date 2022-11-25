package com.example.weather_app.retrofit

import com.example.weather_app.info.City
import org.json.JSONObject

class ObjectMaker {

    fun makeObject(jsonObject: JSONObject) : City{
        var city:City = City(
            jsonObject.getString("name"),
            jsonObject.getJSONObject("coord").getString("lon"),
            jsonObject.getJSONObject("coord").getString("lat"),
            jsonObject.getJSONArray("weather").getJSONObject(0).getString("main"),
            jsonObject.getJSONArray("weather").getJSONObject(0).getString("description"),
            jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon"),
            jsonObject.getJSONObject("main").getString("temp"),
            jsonObject.getJSONObject("main").getString("feels_like"),
            jsonObject.getJSONObject("main").getString("temp_min"),
            jsonObject.getJSONObject("main").getString("temp_max")
        )
        return city
    }

}