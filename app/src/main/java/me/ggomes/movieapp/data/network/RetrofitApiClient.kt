package me.ggomes.movieapp.data.network

import me.ggomes.movieapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApiClient {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val openMovieDbService: OpenMovieDbEndpoints by lazy {
        retrofit.create(OpenMovieDbEndpoints::class.java)
    }
}