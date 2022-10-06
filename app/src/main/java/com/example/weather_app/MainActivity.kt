package com.example.weather_app

import android.content.ContentValues.TAG
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.weather_app.info.MainInfo
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import org.json.JSONObject
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Places.initialize(applicationContext, "AIzaSyDcohma722quXf3lca57RsWk3OSj69Abns")
        val placesClient = Places.createClient(this)
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME))

        var addressList:MutableList<Address>?

        var cityText:TextView = findViewById(R.id.cityText)
        var currentTemperatureText:TextView = findViewById(R.id.currentTemperature)
        var descriptionText:TextView = findViewById(R.id.weatherDescription)
        var coordinatesText:TextView = findViewById(R.id.coordinatesText)

        var urlOne = "https://api.openweathermap.org/data/2.5/weather?"
        var urlTwo = "&units=metric&lang=hr&appid=a0c9131636ddbd513b2be78ca26b3a24"
        var urlCoordinates:String = ""

        var urlComplete:String = ""

        var selectedCity:String = ""

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {

                var geoCoder = Geocoder(applicationContext)

                addressList = geoCoder.getFromLocationName(place.name, 1)
                selectedCity = place.name

            if(addressList!!.size > 0){
                var firstCo:String = (addressList as MutableList<Address>?)!![0].latitude.toString()
                var secondCo:String = (addressList as MutableList<Address>?)!![0].longitude.toString()

                var stringBuilder:StringBuilder

                firstCo = firstCo.substring(0, Math.min(firstCo.length, 5))
                secondCo = secondCo.substring(0, Math.min(secondCo.length, 5))

                urlCoordinates = "lat=" + firstCo + "&lon=" + secondCo
            }else{
                Toast.makeText(applicationContext, "No place found by the name " + place.name + ", please try another.", Toast.LENGTH_SHORT).show()
                autocompleteFragment.setText("Enter a place")
            }

                urlComplete = urlOne + urlCoordinates + urlTwo

            }

            override fun onError(status: Status) {

                Log.i(TAG, "An error occurred: $status")
            }
        })

        val queue = Volley.newRequestQueue(this)


        var cityObject:JSONObject? = null
        var mainInfoObject:MainInfo? = null

        var buttonFind: Button = findViewById(R.id.findButton)

        buttonFind.setOnClickListener(){
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, urlComplete, null,
                { response ->  Log.i("TAG", response.toString())

                    cityObject = JSONObject(response.toString())
                    //loadWeatherInfo(reader)

                    var main: JSONObject = cityObject!!.getJSONObject("main")
                    var cityname:String = cityObject!!.getString("name")


                    mainInfoObject = MainInfo(main.getLong("temp"), main.getLong("feels_like"), main.getLong("temp_min"), main.getLong("temp_max"), main.getLong("pressure"))

                    cityText.text = selectedCity
                    currentTemperatureText.text = "Current temperature: " + mainInfoObject?.temperature.toString()
                    coordinatesText.text =   "Latitude: " + cityObject!!.getJSONObject("coord").getString("lat").toString() + " Longitude: " +cityObject!!.getJSONObject("coord").getString("lon").toString()

                }
            ) { error -> Log.i("TAG", error.toString()) }
            queue.add(jsonObjectRequest)


        }

    }







}