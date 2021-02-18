package me.ggomes.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.ggomes.movieapp.data.network.OpenMovieDbEndpoints
import me.ggomes.movieapp.models.Movie
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val searchTerm: String,
    private val movieDbEndpoints: OpenMovieDbEndpoints
): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val response = movieDbEndpoints.search(searchTerm, page = params.key!!)

            val searchList = response.search
            val movies = searchList.map {
                Movie(it.imdbId, it.title, it.year, it.posterUrl)
            }

            val previousKey = params.key?.let { it - 1 }
            val nextKey = params.key?.let { it + 1 }
            LoadResult.Page(movies, previousKey, nextKey)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}