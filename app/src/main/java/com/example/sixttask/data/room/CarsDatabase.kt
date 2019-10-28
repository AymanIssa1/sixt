package com.example.sixttask.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CarEntity::class], version = 1)
abstract class CarsDatabase : RoomDatabase() {
    abstract fun carsDAO(): CarsDAO
}