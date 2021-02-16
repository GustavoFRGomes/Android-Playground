package me.ggomes.movieapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.ggomes.movieapp.data.repositories.MovieRepository
import me.ggomes.movieapp.data.repositories.abstract.data.Optional
import me.ggomes.movieapp.models.Movie
import org.koin.java.KoinJavaComponent.inject

class MovieListViewModel: BaseViewModel() {

    private val movieRepository: MovieRepository by inject(MovieRepository::class.java)

    fun getMovies(searchTerm: String = "Godzilla"): LiveData<List<Movie>> {
        val movieListLiveData = MutableLiveData<List<Movie>>()

        viewModelScope.launch {
            movieRepository.searchMoviesBy(searchTerm).collect {
                when(it) {
                    is Optional.Success -> movieListLiveData.postValue(it.result)
                    is Optional.Error -> _errorLiveData.postValue(it.throwable)
                }
            }
        }

        return movieListLiveData
    }
}