package com.example.sixttask.di


import com.example.sixttask.domain.CarsRepository
import com.example.sixttask.domain.CarsRepositoryImpl
import com.example.sixttask.ui.carsList.CarsListViewModel
import com.example.sixttask.ui.carsMap.CarsMapViewModel
import com.example.sixttask.ui.main.MainViewModel
import com.example.sixttask.util.coroutines.ApplicationSchedulerProvider
import com.example.sixttask.util.coroutines.SchedulerProvider
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Cars Data Repository
    single<CarsRepository>(createdAtStart = true) { CarsRepositoryImpl(get()) }

    // Rx Schedulers
    single<SchedulerProvider>(createdAtStart = true) { ApplicationSchedulerProvider() }

    viewModel<MainViewModel>()
    viewModel<CarsMapViewModel>()
    viewModel<CarsListViewModel>()
}