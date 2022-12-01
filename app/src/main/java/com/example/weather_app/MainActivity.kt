package com.example.weather_app

//import com.android.volley.Response

import android.content.ContentValues.TAG
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.weather_app.database.AppDatabase
import com.example.weather_app.database.CityModel
import com.example.weather_app.info.City
import com.example.weather_app.location.LocationMngr
import com.example.weather_app.permission.PermissionManager
import com.example.weather_app.recyclerview.CityAdapter
import com.example.weather_app.retrofit.RetrofitManager
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cityDB = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "city"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()

        var cities = cityDB.cityDao().getAll()
        var recView:RecyclerView = findViewById(R.id.recView)



        var adapter = CityAdapter(cities as ArrayList<CityModel>)
        recView.adapter = adapter
        recView.layoutManager = LinearLayoutManager(this)




        Places.initialize(applicationContext, "AIzaSyDcohma722quXf3lca57RsWk3OSj69Abns")
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME))

        //list of adress that google API returns when user searches
        var addressList:MutableList<Address>?

        var lastUpdatedText:TextView = findViewById(R.id.lastUpdatedText)
        var dateInfo: List<String> = cityDB.cityDao().getAll().get(0).lastUpdated!!.split(" ")
        lastUpdatedText.text = dateInfo[0] + " " + dateInfo[1] + " " + dateInfo[2] + " " + dateInfo[3]


        var cityText:TextView = findViewById(R.id.cityText)
        var currentTemperatureText:TextView = findViewById(R.id.currentTemperature)
        var descriptionText:TextView = findViewById(R.id.weatherDescription)

        //TEMPORARY CITY OBJECT VALUES
        var tempName:String?

        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        var button: Button = findViewById(R.id.button)
        var refreshButton:Button = findViewById(R.id.refreshButton)

        refreshButton.setOnClickListener(){
            RetrofitManager().syncAllCities(cityDB.cityDao(), adapter, lastUpdatedText)
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
                val userDao = cityDB.cityDao()
                RetrofitManager().getCity(tempName!!, cityText, currentTemperatureText, descriptionText, userDao, adapter, lastUpdatedText)


            }

            override fun onError(status: Status) {

                Log.i(TAG, "An error occurred: $status")
            }
        })

        button.setOnClickListener(){
            PermissionManager.checkLocationPermission(this)
            var listOfParams = LocationMngr.getCurrentLocation(locationManager)

            RetrofitManager().getCityWithCo(listOfParams.get(0),listOfParams.get(1), cityText, currentTemperatureText, descriptionText,cityDB.cityDao(), adapter, lastUpdatedText)
        }



    }







}