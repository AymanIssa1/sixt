package com.example.sixttask

import androidx.room.Room
import com.example.sixttask.data.room.CarsDatabase
import org.koin.dsl.module

// Room in memory
val roomTestModule = module(override = true) {
    single {
        Room.inMemoryDatabaseBuilder(get(), CarsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
}