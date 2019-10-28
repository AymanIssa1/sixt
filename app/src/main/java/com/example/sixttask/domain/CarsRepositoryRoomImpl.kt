package com.example.sixttask.domain

import android.util.Log
import com.example.sixttask.data.json.Car
import com.example.sixttask.data.room.CarEntity
import com.example.sixttask.data.room.CarsDAO
import com.example.sixttask.remote.RemoteDataSource
import io.reactivex.Single

class CarsRepositoryRoomImpl(
    private val remoteDataSource: RemoteDataSource,
    private val carsDAO: CarsDAO
) : CarsRepository {

    override fun getCars(): Single<List<Car>> {
        return carsDAO.getAllCars()
            .map { carsEntities ->
                if (carsEntities.isEmpty()) {
                    Log.e("getCars", "${carsEntities.size}")
                    getCarsFromRemote()
                } else {
                    Log.e("getCars", "${carsEntities.size}")
                    getCarsFromEntities(
                        carsEntities
                    )
                }
            }
    }

    private fun getCarsFromEntities(carEntities: List<CarEntity>): List<Car> {
        return carEntities.map { carEntity -> Car.from(carEntity) }
    }

    private fun getCarsFromRemote(): List<Car> {
        return remoteDataSource.getCars().doOnSuccess {
            carsDAO.saveAllCars(it.map { car -> CarEntity.from(car) })
        }.blockingGet()
    }

}