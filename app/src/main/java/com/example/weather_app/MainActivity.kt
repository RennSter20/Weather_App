package com.example.weather_app

//import com.android.volley.Response

import android.content.ContentValues.TAG
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.weather_app.database.AppDatabase
import com.example.weather_app.database.CityModel
import com.example.weather_app.info.City
import com.example.weather_app.location.LocationMngr
import com.example.weather_app.permission.PermissionManager
import com.example.weather_app.retrofit.ApiInterface
import com.example.weather_app.retrofit.ObjectFiller
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "CityModel"
        ).allowMainThreadQueries().build()


        Places.initialize(applicationContext, "AIzaSyDcohma722quXf3lca57RsWk3OSj69Abns")

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

        var urlOne = "https://api.openweathermap.org/data/2.5/"
        var urlTwo = "&units=metric&lang=en&appid=e823ed3a89e6e68ab2ff121613a7cf70"

        //TEMPORARY CITY OBJECT VALUES
        var tempName:String? = null
        var tempLon:String? = null
        var tempLat:String? = null
        var tempMainDesc:String
        var tempDesc:String
        var tempIcon:String? = null
        var tempTemp:String? = null
        var tempFeelsLike:Long
        var tempMin:Long
        var tempMax:Long
        var tempWind:Long
        var tempUrl:String? = null
        //

        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager


        var button: Button = findViewById(R.id.button)

        fun setIcon(){
            var iconString:String = city!!.cityIcon.toString()
            var image:ImageView = findViewById(R.id.imageView)

            val res: Resources = resources
            val mDrawableName = "_" + iconString.substring(1)
            val resID: Int = res.getIdentifier(mDrawableName, "drawable", packageName)
            val drawable: Drawable = res.getDrawable(resID)
            image.setImageDrawable(drawable)
        }

        var retrofit: Retrofit? = null
        fun getClient(): Retrofit? { //creating object
            if (retrofit == null) {
                retrofit =
                    Retrofit.Builder() //Retrofit.Builder class uses the Builder API to allow defining the URL end point for the HTTP operations and finally build a new Retrofit instance.
                        //http://api.openweathermap.org/data/2.5/weather?q=London&APPID=76a35a17f3e1bce771a09f3555b614a8
                        .baseUrl("https://api.openweathermap.org/data/2.5/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
        fun getInfo(){
            var apiInterface:ApiInterface = getClient()!!.create(ApiInterface::class.java)
            var call: Call<Any?> = apiInterface.getWeatherData(tempName)
            call?.enqueue(object : Callback<Any?> {

                override fun onFailure(call: Call<Any?>, t: Throwable?) {}
                override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                    var json:JSONObject = response.body()
                    var objFiller:ObjectFiller? = null
                    city = objFiller?.createCityObject(json)
                }
            })
        }

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {

                var geoCoder = Geocoder(applicationContext)

                //returned search results
                addressList = geoCoder.getFromLocationName(place.name, 1)

                tempName = place.name.toString()
                if(addressList!!.size == 0 ) {
                    Toast.makeText(
                        applicationContext,
                        "No place found by the name " + place.name + ", please try another.",
                        Toast.LENGTH_LONG
                    ).show()
                    autocompleteFragment.setText("Enter a place")
                }

                getInfo()


            }

            override fun onError(status: Status) {

                Log.i(TAG, "An error occurred: $status")
            }
        })

        button.setOnClickListener(){
            PermissionManager.checkLocationPermission(this)
            var listOfParams = LocationMngr.getCurrentLocation(locationManager)

            getInfo()

            cityText.text = city?.cityName
            currentTemperatureText.text = city?.temperature
            descriptionText.text = city?.cityMainDescription
        }



    }







}