package com.example.sixttask.remote

import com.example.sixttask.data.json.Car
import io.reactivex.Single
import retrofit2.http.GET

interface RemoteDataSource {

    @GET("/codingtask/cars")
    fun getCars(): Single<List<Car>>

}