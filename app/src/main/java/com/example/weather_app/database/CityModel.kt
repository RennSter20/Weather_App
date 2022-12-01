package com.example.weather_app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "city")
data class CityModel(
    @PrimaryKey val uid: Int?,
    @ColumnInfo(name = "cityName") val cityName: String?,
    @ColumnInfo(name = "temperature") val temperature: String?,
    @ColumnInfo(name = "lastUpdated") val lastUpdated: String?
)
