package com.example.sixttask.di

import androidx.room.Room
import com.example.sixttask.data.room.CarsDatabase
import com.example.sixttask.domain.CarsRepository
import com.example.sixttask.domain.CarsRepositoryRoomImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomDataSourceModule = module {

    // Room Database Repo
    single<CarsRepository>(override = true) {
        CarsRepositoryRoomImpl(get(), get())
    }

    // Room Database
    single {
        Room.databaseBuilder(androidApplication(), CarsDatabase::class.java, "cars-db").build()
    }

    // Expose CarsDAO
    single { get<CarsDatabase>().carsDAO() }

}