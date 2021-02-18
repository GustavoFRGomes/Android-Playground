package me.ggomes.movieapp.data.network

import me.ggomes.movieapp.BuildConfig
import me.ggomes.movieapp.data.dto.MovieDetailsResponse
import me.ggomes.movieapp.data.dto.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMovieDatabaseService {

    @GET("/?apiKey=${BuildConfig.API_KEY}")
    suspend fun search(
            @Query("s") searchTerm: String,
            @Query("page") page: Int = 1
    ): MovieResponse

    @GET("/?apiKey=${BuildConfig.API_KEY}")
    suspend fun getMovieBy(@Query("i") imdbId: String): MovieDetailsResponse
}