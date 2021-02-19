package me.ggomes.movieapp.di

import me.ggomes.movieapp.data.database.MovieDatabase
import me.ggomes.movieapp.data.network.RetrofitApiClient
import me.ggomes.movieapp.data.repositories.MovieRepository
import me.ggomes.movieapp.viewmodels.MovieDetailsViewModel
import me.ggomes.movieapp.viewmodels.MovieListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModules = module {
    single { MovieDatabase.getInstance(androidContext()) }
    single { RetrofitApiClient.openMovieDbService }

    single { MovieRepository(get(), get())}

    viewModel { MovieDetailsViewModel(get()) }
    viewModel { MovieListViewModel(get()) }
}