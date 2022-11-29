package com.example.weather_app.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.weather_app.info.City


@Dao
interface CityDao {
    @Query("SELECT * FROM city")
    fun getAll(): List<CityModel>

    @Query("SELECT * FROM city WHERE uid IN (:cityIds)")
    fun loadAllByIds(cityIds: IntArray): List<CityModel>

    @Insert
    fun insertAll(vararg users: CityModel)

    @Delete
    fun delete(user: CityModel)
}