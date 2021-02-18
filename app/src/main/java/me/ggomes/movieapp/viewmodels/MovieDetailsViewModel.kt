package me.ggomes.movieapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.ggomes.movieapp.data.repositories.MovieRepository
import me.ggomes.movieapp.models.MovieDetail
import org.koin.java.KoinJavaComponent.inject

class MovieDetailsViewModel: BaseViewModel() {

    private val movieRepository: MovieRepository by inject(MovieRepository::class.java)

    fun getMovieDetailsBy(movieId: String): LiveData<MovieDetail> {
        val responseLiveData = MutableLiveData<MovieDetail>()
        viewModelScope.launch {
            movieRepository.getMovieBy(movieId).catch { cause ->
                internalErrorLiveData.postValue(cause)
            }.collect {
                responseLiveData.postValue(it)
            }
        }
        return responseLiveData
    }
}