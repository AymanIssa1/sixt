package com.example.sixttask.ui.carsMap

import com.example.sixttask.domain.CarsRepository
import com.example.sixttask.ui.main.MainViewModel
import com.example.sixttask.util.coroutines.SchedulerProvider

class CarsMapViewModel(
    private val carsRepository: CarsRepository,
    private val schedulerProvider: SchedulerProvider
) : MainViewModel(carsRepository, schedulerProvider)
