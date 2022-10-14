package com.example.weather_app.database

import androidx.room.*
import com.example.weather_app.info.City

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(vararg cities: CityObject)

    @Query("SELECT * FROM city")
    fun getAll(): List<CityObject>

    @Query("SELECT * FROM city WHERE cid IN (:cityIds)")
    fun loadAllByIds(cityIds: IntArray): List<CityObject>

    @Query("SELECT * FROM city WHERE city_name LIKE :city_name AND " +
            "temperature LIKE :temperature LIMIT 1")
    fun findByName(city_name: String, temperature: Long): CityObject

    @Insert
    fun insertAll(vararg cities: CityObject)

    @Delete
    fun delete(city: CityObject)

    @Query("SELECT * FROM city WHERE city_name LIKE :cityName")
    fun loadCityWithName(cityName: String): CityObject
}