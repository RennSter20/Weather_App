package com.example.weather_app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityObject(
    @PrimaryKey val cid: Int,
    @ColumnInfo(name = "city_name") val city_name: String?,
    @ColumnInfo(name = "temperature") val temperature: Long?,
    @ColumnInfo(name = "longitude") val longitude: String?,
    @ColumnInfo(name = "latitude") val latitude: String?,
)