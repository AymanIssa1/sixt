package com.example.sixttask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.sixttask.data.json.Car
import com.example.sixttask.di.appModule
import com.example.sixttask.di.remoteDataSourceModule
import com.example.sixttask.domain.CarsRepository
import com.example.sixttask.ui.main.CarsUIModel
import com.example.sixttask.ui.main.MainViewModel
import com.example.sixttask.utils.TestSchedulerProvider
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest : KoinTest {

    lateinit var mainViewModel: MainViewModel

    @Mock
    lateinit var carsRepository: CarsRepository
    @Mock
    lateinit var carsObserver: Observer<CarsUIModel>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        startKoin {
            modules(listOf(appModule, remoteDataSourceModule()))
        }
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(carsRepository, TestSchedulerProvider())
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun testGetCarsSuccess() {
        val returnedData = ArrayList<Car>()

        Mockito.`when`(carsRepository.getCars()).thenReturn(Single.just(returnedData))

        mainViewModel.carsUIModel.observeForever(carsObserver)

        mainViewModel.getCars()

        Mockito.verify(carsObserver)
            .onChanged(CarsUIModel(isSuccess = true, cars = returnedData))
    }

    @Test
    fun testGetCarsFailed() {
        val returnedData = IllegalStateException("error!")

        Mockito.`when`(carsRepository.getCars()).thenReturn(Single.error(returnedData))

        mainViewModel.carsUIModel.observeForever(carsObserver)

        mainViewModel.getCars()

        Mockito.verify(carsObserver).onChanged(CarsUIModel(error = returnedData))

    }

}