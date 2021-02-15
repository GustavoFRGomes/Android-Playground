package me.ggomes.movieapp.data.network

import me.ggomes.movieapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMovieDbEndpoints {

    @GET("/?apiKey=${BuildConfig.API_KEY}")
    fun search(@Query("s") searchTerm: String)

    @GET("/?apiKey=${BuildConfig.API_KEY}")
    fun getMovieBy(@Query("i") imdbId: String)
}