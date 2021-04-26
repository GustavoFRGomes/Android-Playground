package me.ggomes.demo.common.di

import me.ggomes.demo.common.ErrorHandler
import me.ggomes.demo.common.mappers.ImageDtoToVehicleMapper
import me.ggomes.demo.data.network.RetrofitApiClient
import me.ggomes.demo.data.repositories.MobileDeRepository
import me.ggomes.demo.detail.viewmodel.DetailsViewModel
import me.ggomes.demo.gallery.viewmodel.GalleryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModules = module {
    // Data Dependencies
    single { RetrofitApiClient.MOBILE_DE_API_SERVICE }
    single { MobileDeRepository(get())}
    // Mapper from DTO to UI Model
    single { ImageDtoToVehicleMapper() }

    // General Error Handler
    single {ErrorHandler(get())}

    // View Models
    viewModel { GalleryViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}