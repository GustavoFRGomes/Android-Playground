package me.ggomes.movieapp.data.repositories

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import me.ggomes.movieapp.data.database.MovieDatabase
import me.ggomes.movieapp.data.network.OpenMovieDbEndpoints
import me.ggomes.movieapp.models.Movie
import me.ggomes.movieapp.models.MovieKeys
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class PageRemoteMediator(
    private val movieApi: OpenMovieDbEndpoints,
    private val movieDatabase: MovieDatabase,
    private val searchTerm: String
): RemoteMediator<Int, Movie>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    getMovieKeys()
                }
            }

            val page = loadKey?.id ?: 1
            val response = movieApi.search(searchTerm, page = page)


            val searchResponse = response.search
            val movies = searchResponse.map { movieResponse ->
                Movie(
                        movieResponse.imdbId,
                        movieResponse.title,
                        movieResponse.year,
                        movieResponse.posterUrl
                )
            }

            if (movies.isNotEmpty()) {
                movieDatabase.movieDao().saveMovies(movies)
                movieDatabase.movieKeysDao().saveMovieKeys(MovieKeys(page + 1))
            }

            MediatorResult.Success(endOfPaginationReached = movies.size != 10)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun getMovieKeys(): MovieKeys? {
        return movieDatabase.movieKeysDao().getMovieKeys().firstOrNull()
    }
}