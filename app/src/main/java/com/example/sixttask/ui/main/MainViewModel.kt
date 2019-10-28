package com.example.sixttask.ui.main

import androidx.lifecycle.MutableLiveData
import com.example.sixttask.data.json.Car
import com.example.sixttask.domain.CarsRepository
import com.example.sixttask.util.coroutines.SchedulerProvider
import com.example.sixttask.util.coroutines.with
import com.example.sixttask.util.mvvm.RxViewModel

open class MainViewModel(
    private val carsRepository: CarsRepository,
    private val schedulerProvider: SchedulerProvider
) : RxViewModel() {

    val carsUIModel = MutableLiveData<CarsUIModel>()

    fun getCars() {
        launch {
            carsUIModel.postValue(CarsUIModel(isLoading = true))
            carsRepository
                .getCars()
                .with(schedulerProvider)
                .subscribe({
                    carsUIModel.postValue(
                        CarsUIModel(
                            isSuccess = true,
                            cars = it
                        )
                    )
                }, {
                    carsUIModel.postValue(CarsUIModel(error = it))
                })
        }
    }

}

data class CarsUIModel(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val cars: List<Car>? = null,
    val error: Throwable? = null
)