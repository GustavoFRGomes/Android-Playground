package me.ggomes.movieapp.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import me.ggomes.movieapp.data.repositories.MovieRepository
import me.ggomes.movieapp.models.Movie
import org.koin.java.KoinJavaComponent.inject

class MovieListViewModel: BaseViewModel() {

    private val movieRepository: MovieRepository by inject(MovieRepository::class.java)

    @ExperimentalPagingApi
    fun getMovies(searchTerm: String = "dog"): Flow<PagingData<Movie>> {
        return movieRepository.searchMoviesBy(searchTerm).cachedIn(viewModelScope)
    }
}