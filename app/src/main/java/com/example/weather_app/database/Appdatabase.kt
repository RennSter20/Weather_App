package com.example.weather_app.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CityObject::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}