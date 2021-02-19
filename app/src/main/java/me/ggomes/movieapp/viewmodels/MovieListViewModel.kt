package me.ggomes.movieapp.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import me.ggomes.movieapp.data.repositories.MovieRepository
import me.ggomes.movieapp.models.Movie

class MovieListViewModel(
    private val movieRepository: MovieRepository
): BaseViewModel() {

    @ExperimentalPagingApi
    fun getMovies(searchTerm: String = "dog"): Flow<PagingData<Movie>> {
        return movieRepository.searchMoviesBy(searchTerm).cachedIn(viewModelScope)
    }
}