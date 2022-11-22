package com.example.weather_app.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityModelDao {

    @Query("SELECT * FROM CityModel")
    fun getAll(): List<CityModel>

    @Insert
    fun insertAll(vararg cities: CityModel)



}