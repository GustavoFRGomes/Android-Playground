package me.ggomes.movieapp.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.ggomes.movieapp.data.database.MovieDatabase
import me.ggomes.movieapp.data.network.OpenMovieDatabaseService
import me.ggomes.movieapp.data.paging.PageRemoteMediator
import me.ggomes.movieapp.models.Movie
import me.ggomes.movieapp.models.MovieDetail
import retrofit2.HttpException

class MovieRepository(
    private val database: MovieDatabase,
    private val apiService: OpenMovieDatabaseService
) {
    @ExperimentalPagingApi
    fun searchMoviesBy(term: String): Flow<PagingData<Movie>> {
        return Pager(
            PagingConfig(pageSize = 10, enablePlaceholders = false, prefetchDistance = 5),
            remoteMediator = PageRemoteMediator(apiService, database, term),
            pagingSourceFactory = {
                database.movieDao().getAllMovies()
            }
        ).flow
    }

    fun getMovieBy(movieId: String): Flow<MovieDetail> = flow {
        val databaseResult = database.movieDetailDao().getDetailsBy(movieId)
        databaseResult?.let {
            emit(it)
        }

        try {
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
        } catch (exception: HttpException) {
            // Throw exception only if Database didn't provide results as well
            if (databaseResult == null)
                throw exception
        }
    }
}