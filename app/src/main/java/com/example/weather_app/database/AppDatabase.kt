package com.example.weather_app.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CityModel::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}