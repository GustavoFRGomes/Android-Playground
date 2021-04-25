package me.ggomes.demo.di

import me.ggomes.demo.data.database.MovieDatabase
import me.ggomes.demo.data.network.RetrofitApiClient
import me.ggomes.demo.data.repositories.MobileDeRepository
import me.ggomes.demo.viewmodels.VehicleListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModules = module {
    single { MovieDatabase.getInstance(androidContext()) }
    single { RetrofitApiClient.MOBILE_DE_API_SERVICE }

    single { MobileDeRepository(get())}

    viewModel { VehicleListViewModel(get()) }
}