package com.example.weather_app.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat.getSystemService

class LocationMngr {



    companion object{
        @SuppressLint("MissingPermission")
        fun getCurrentLocation(locationManager: LocationManager):String{

            val lastKnownLocationByGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            var location: Location? = null

            var urlCoordinates:String? = null

            lastKnownLocationByGps?.let {
                location = lastKnownLocationByGps

                var latitude:Double = location!!.latitude
                var longitude:Double = location!!.longitude

                var urlOne = "https://api.openweathermap.org/data/2.5/weather?"
                var urlTwo = "&units=metric&lang=en&appid=a0c9131636ddbd513b2be78ca26b3a24"

                urlCoordinates = urlOne + "lat=" + latitude + "&lon=" + longitude + urlTwo

            }

            return urlCoordinates!!

        }

        @SuppressLint("MissingPermission")
        fun getLatitude(locationManager: LocationManager): Double {
            val lastKnownLocationByGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            var location: Location? = lastKnownLocationByGps
            return location!!.latitude
        }

        @SuppressLint("MissingPermission")
        fun getLongitude(locationManager: LocationManager): Double {
            val lastKnownLocationByGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            var location: Location? = lastKnownLocationByGps
            return location!!.longitude
        }

    }
}