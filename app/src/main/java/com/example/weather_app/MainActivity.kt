package com.example.weather_app

import android.content.ContentValues.TAG
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.weather_app.info.MainInfo
import com.example.weather_app.info.Weather
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Initialize the SDK
        Places.initialize(applicationContext, "AIzaSyDcohma722quXf3lca57RsWk3OSj69Abns")

        // Create a new PlacesClient instance
        val placesClient = Places.createClient(this)

        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME))

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {

                var geoCoder = Geocoder(applicationContext)

                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: ${place.name}, ${place.id}")
                Log.i("LOCATION", geoCoder.getFromLocationName(place.name, 1).toString())
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: $status")
            }
        })


        var textView:TextView = findViewById(R.id.text)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openweathermap.org/data/2.5/weather?lat=43.73&lon=15.89&units=metric&lang=hr&appid=a0c9131636ddbd513b2be78ca26b3a24"

        var weather: Weather? = null
        var mainInfo:MainInfo? = null


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->  Log.i("TAG", response.toString())

                val reader = JSONObject(response.toString())
                //loadWeatherInfo(reader)

                var main: JSONObject = reader.getJSONObject("main")


                var mainInfoObject:MainInfo = MainInfo(main.getLong("temp"), main.getLong("feels_like"), main.getLong("temp_min"), main.getLong("temp_max"), main.getLong(("pressure")))

                Log.i("WEATHER", weather?.main.toString() + "" + weather?.description.toString())
                Log.i("MAIN INFO", mainInfo.toString())
            }
        ) { error -> Log.i("TAG", error.toString()) }

        queue.add(jsonObjectRequest)


    }







}