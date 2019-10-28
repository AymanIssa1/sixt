package com.example.sixttask.domain

import com.example.sixttask.data.json.Car
import com.example.sixttask.remote.RemoteDataSource
import io.reactivex.Single

interface CarsRepository {

    fun getCars(): Single<List<Car>>

}

class CarsRepositoryImpl(private val remoteDataSource: RemoteDataSource) : CarsRepository {

    private var carsCache = ArrayList<Car>()

    override fun getCars(): Single<List<Car>> {
        return if (carsCache.isNotEmpty())
            Single.just(carsCache)
        else
            remoteDataSource
                .getCars()
                .map {
                    carsCache.clear()
                    carsCache = it as ArrayList<Car>
                    it
                }

    }

}