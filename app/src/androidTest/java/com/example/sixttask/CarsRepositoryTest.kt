package com.example.sixttask

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sixttask.domain.CarsRepository
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
class CarsRepositoryTest : KoinTest {

    private val carsRepositoryTest: CarsRepository by inject()

    @Before
    fun before() {
        loadKoinModules(roomTestModule)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun testGetCars() {
        val carsFromRemote = carsRepositoryTest.getCars().blockingGet()
        val carsFromDB = carsRepositoryTest.getCars().blockingGet()
        Assert.assertEquals(carsFromRemote, carsFromDB)
    }

}