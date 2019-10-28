package com.example.sixttask

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sixttask.data.room.CarEntity
import com.example.sixttask.data.room.CarsDAO
import com.example.sixttask.data.room.CarsDatabase
import com.example.sixttask.remote.RemoteDataSource
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class CarsDAOTest : KoinTest {

    val carsDatabase: CarsDatabase by inject()
    val remoteDataSource: RemoteDataSource by inject()
    val carsDAO: CarsDAO by inject()

    @Before
    fun before() {
        loadKoinModules(roomTestModule)
    }

    @After
    fun after() {
        carsDatabase.close()
        stopKoin()
    }

    @Test
    fun testInsert() {
        val carsEntities = getCarsAsEntities()

        carsDAO.saveAllCars(carsEntities)

        val entitiesFromDB = carsDAO.getAllCars().blockingGet()

        Assert.assertEquals(entitiesFromDB, carsEntities)
    }

    fun getCarsAsEntities(): List<CarEntity> {
        return remoteDataSource.getCars()
            .map { cars -> cars.map { car -> CarEntity.from(car) } }
            .blockingGet()
    }

}