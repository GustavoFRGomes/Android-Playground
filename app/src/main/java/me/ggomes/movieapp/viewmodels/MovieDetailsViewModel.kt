package me.ggomes.movieapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.ggomes.movieapp.data.dto.MovieDetailsResponse
import me.ggomes.movieapp.data.network.RetrofitApiClient
import me.ggomes.movieapp.data.repositories.MovieRepository
import me.ggomes.movieapp.data.repositories.abstract.data.Optional
import me.ggomes.movieapp.models.MovieDetail
import org.koin.java.KoinJavaComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsViewModel: BaseViewModel() {

    private val movieRepository: MovieRepository by KoinJavaComponent.inject(MovieRepository::class.java)

    fun getMovieDetailsBy(movieId: String): LiveData<MovieDetail> {
        val responseLiveData = MutableLiveData<MovieDetail>()
        viewModelScope.launch {
            movieRepository.getMovieBy(movieId).collect {
                when (it) {
                    is Optional.Success -> responseLiveData.postValue(it.result)
                    is Optional.Error -> _errorLiveData.postValue(it.throwable)
                }
            }
        }
        return responseLiveData
    }
}