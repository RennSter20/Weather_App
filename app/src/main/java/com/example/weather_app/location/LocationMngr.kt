package com.example.weather_app.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat.getSystemService

class LocationMngr {



    companion object{
        @SuppressLint("MissingPermission")
        fun getCurrentLocation(locationManager: LocationManager):List<String>{

            val lastKnownLocationByGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            var location: Location?
            var stringArray = mutableListOf<String>()

            lastKnownLocationByGps?.let {
                location = lastKnownLocationByGps

                var latitude:Double = location!!.latitude
                var longitude:Double = location!!.longitude

                stringArray += latitude.toString()
                stringArray += longitude.toString()

            }

            return stringArray

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