package com.example.sixttask.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface CarsDAO {

    @Insert
    fun saveAllCars(entities: List<CarEntity>)

    @Query("SELECT * FROM cars")
    fun getAllCars(): Single<List<CarEntity>>

    @Query("SELECT * FROM cars WHERE id = :id")
    fun findCarById(id: String): Single<CarEntity>

}