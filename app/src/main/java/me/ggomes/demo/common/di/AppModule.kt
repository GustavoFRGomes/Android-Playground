package me.ggomes.demo.common.di

import me.ggomes.demo.data.network.RetrofitApiClient
import me.ggomes.demo.data.repositories.MobileDeRepository
import me.ggomes.demo.gallery.viewmodel.VehicleListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModules = module {
    single { RetrofitApiClient.MOBILE_DE_API_SERVICE }

    single { MobileDeRepository(get())}

    viewModel { VehicleListViewModel(get()) }
}