package me.ggomes.movieapp.data.repositories

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.ggomes.movieapp.data.database.MovieDatabase
import me.ggomes.movieapp.data.dto.MovieResponse
import me.ggomes.movieapp.data.network.OpenMovieDbEndpoints
import me.ggomes.movieapp.models.Movie
import me.ggomes.movieapp.models.MovieDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(
    private val database: MovieDatabase,
    private val apiService: OpenMovieDbEndpoints
) {
    fun searchMoviesBy(term: String): Flow<List<Movie>> = flow {
        val databaseResults = database.movieDao().searchBy("%$term%")
        if (databaseResults.isNotEmpty())
            emit(databaseResults)

        val response = apiService.search(term)
        val movies = response.search.map {
            Movie(it.imdbId, it.title, it.year, it.posterUrl)
        }.distinct()

        if (movies != databaseResults) {
            emit(movies)

            movies.forEach { movie ->
                if (!databaseResults.contains(movie))
                    database.movieDao().insert(movie)
            }
        }

    }

    fun getMovieBy(movieId: String): Flow<MovieDetail> = flow {
        val databaseResult = database.movieDetailDao().getDetailsBy(movieId)
        databaseResult?.let {
            emit(it)
        }

        val networkResponse = apiService.getMovieBy(movieId)
        val movie = MovieDetail(
                movieId,
                networkResponse.title,
                networkResponse.posterUrl,
                networkResponse.plot,
                networkResponse.year,
                networkResponse.actors,
                networkResponse.director
        )

        if (databaseResult != movie) {
            database.movieDetailDao().delete(movieId)
            database.movieDetailDao().insert(movie)
            emit(movie)
        }
    }
}