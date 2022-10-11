package com.example.weather_app

import android.content.ContentValues.TAG
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.weather_app.info.City
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


        Places.initialize(applicationContext, "AIzaSyDcohma722quXf3lca57RsWk3OSj69Abns")
        val placesClient = Places.createClient(this)
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME))

        //list of adress that google API returns when user searches
        var addressList:MutableList<Address>?

        //main city object
        var city:City? = null

        var cityText:TextView = findViewById(R.id.cityText)
        var currentTemperatureText:TextView = findViewById(R.id.currentTemperature)
        var descriptionText:TextView = findViewById(R.id.weatherDescription)
        var coordinatesText:TextView = findViewById(R.id.coordinatesText)

        var urlOne = "https://api.openweathermap.org/data/2.5/weather?"
        var urlTwo = "&units=metric&lang=en&appid=a0c9131636ddbd513b2be78ca26b3a24"
        var urlCoordinates:String = ""
        var urlComplete:String = ""

        //TEMPORARY CITY OBJECT VALUES
        var tempName:String? = null
        var tempLon:String? = null
        var tempLat:String? = null

        var tempMainDesc:String
        var tempDesc:String
        var tempIcon:String

        var tempTemp:Long
        var tempFeelsLike:Long
        var tempMin:Long
        var tempMax:Long
        var tempWind:Long

        var tempUrl:String? = null
        //


        fun setIcon(){
            var iconString:String = city!!.cityIcon.toString()
            var image:ImageView = findViewById(R.id.imageView)

            val res: Resources = resources
            val mDrawableName = "_" + iconString.substring(1)
            val resID: Int = res.getIdentifier(mDrawableName, "drawable", packageName)
            val drawable: Drawable = res.getDrawable(resID)
            image.setImageDrawable(drawable)
        }

        fun showInfo(){
            val queue = Volley.newRequestQueue(this)

            //response
            var cityObject:JSONObject? = null


            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, urlComplete, null,
                { response ->

                    cityObject = JSONObject(response.toString())

                    var main: JSONObject = cityObject!!.getJSONObject("main")
                    var cityname:String = cityObject!!.getString("name")

                    city = City(
                        tempName,
                        tempLon,
                        tempLat,
                        cityObject!!.getJSONArray("weather").getJSONObject(0).getString("main"),
                        cityObject!!.getJSONArray("weather").getJSONObject(0)
                            .getString("description"),
                        cityObject!!.getJSONArray("weather").getJSONObject(0).getString("icon"),
                        cityObject!!.getJSONObject("main").getLong("temp"),
                        cityObject!!.getJSONObject("main").getLong("feels_like"),
                        cityObject!!.getJSONObject("main").getLong("temp_min"),
                        cityObject!!.getJSONObject("main").getLong("temp_max"),
                        cityObject!!.getJSONObject("wind").getLong("speed"),
                        tempUrl,
                    )

                    //TO DO CITY TEXT
                    cityText.text = city!!.cityName
                    currentTemperatureText.text = "Current temperature: " + city?.temperature.toString()

                    //TESTING IF COORDINATES ARE SHOWN
                    coordinatesText.text =   "Latitude: " + city!!.cityLat + " Longitude: " + city!!.cityLon

                    descriptionText.text = city!!.cityDescription

                    setIcon()
                }
            ) { error -> Log.i("TAG", error.toString()) }

            queue.add(jsonObjectRequest)
        }

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {

                var geoCoder = Geocoder(applicationContext)

                //returned search results
                addressList = geoCoder.getFromLocationName(place.name, 1)

                //TO DO --- get place.name
                tempName = place.name.toString()

            if(addressList!!.size > 0 ){
                var firstCo:String = (addressList as MutableList<Address>?)!![0].latitude.toString()
                var secondCo:String = (addressList as MutableList<Address>?)!![0].longitude.toString()


                //coordinates have too much decimals, this step removes extra decimals
                var stringBuilder:StringBuilder
                firstCo = firstCo.substring(0, Math.min(firstCo.length, 5))
                secondCo = secondCo.substring(0, Math.min(secondCo.length, 5))
                tempLat = firstCo
                tempLon = secondCo


                //this is final
                urlCoordinates = "lat=" + firstCo + "&lon=" + secondCo
                urlComplete = urlOne + urlCoordinates + urlTwo
                tempUrl = urlComplete

            }else{
                Toast.makeText(applicationContext, "No place found by the name " + place.name + ", please try another.", Toast.LENGTH_LONG).show()

                //TO DO --- fix search bar text after not finding a suitable place user wanted
                autocompleteFragment.setText("Enter a place")
            }
                showInfo()
            }

            override fun onError(status: Status) {

                Log.i(TAG, "An error occurred: $status")
            }
        })






    }







}