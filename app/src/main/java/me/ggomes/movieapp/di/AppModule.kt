package me.ggomes.movieapp.di

import me.ggomes.movieapp.data.database.MovieDatabase
import me.ggomes.movieapp.data.network.RetrofitApiClient
import me.ggomes.movieapp.data.repositories.MovieRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModules = module {
    single { MovieDatabase.getInstance(androidContext()) }
    single { RetrofitApiClient.openMovieDbService }

    single { MovieRepository(get(), get())}
}