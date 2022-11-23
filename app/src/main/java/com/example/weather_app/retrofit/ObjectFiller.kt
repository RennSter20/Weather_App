package com.example.weather_app.retrofit

import com.example.weather_app.info.City
import com.example.weather_app.retrofit.main.Main
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response

class ObjectFiller {

    fun createCityObject(response: JSONObject?): City? {

        var city:City

        var main: JSONObject? = response?.getJSONObject("main")
        var weather:JSONObject? = response?.getJSONArray("weater")?.getJSONObject(0)

        return City(response?.getString("name"),
                    response?.getJSONObject("coord")?.getString("lon"),
                    response?.getJSONObject("coord")?.getString("lat"),
                    weather?.getString("main"),
                    weather?.getString("description"),
                    weather?.getString("icon"),
                    main?.getString("temp"),
                    main?.getString("feels_like"),
                    main?.getString("temp_min"),
                    main?.getString("temp_max"))

    }

}