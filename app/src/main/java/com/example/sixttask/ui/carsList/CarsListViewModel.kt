package com.example.sixttask.ui.carsList

import com.example.sixttask.domain.CarsRepository
import com.example.sixttask.ui.main.MainViewModel
import com.example.sixttask.util.coroutines.SchedulerProvider

class CarsListViewModel(
    private val carsRepository: CarsRepository,
    private val schedulerProvider: SchedulerProvider
) : MainViewModel(carsRepository, schedulerProvider)