package com.example.weather_app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CityModel")
data class CityModel(

    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name= "city_name") var city_name:String,
    @ColumnInfo(name = "temperature") var temperature: Long?


)
