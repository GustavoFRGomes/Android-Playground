package me.ggomes.movieapp.viewmodels

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import me.ggomes.movieapp.data.database.MovieDatabase
import me.ggomes.movieapp.data.repositories.MovieRepository
import me.ggomes.movieapp.models.Movie
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieListViewModelTest {

    private lateinit var SUT: MovieListViewModel
    private lateinit var repository: MovieRepository
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        repository = mockk()
        SUT = MovieListViewModel(repository)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @ExperimentalPagingApi
    @Test
    fun `verify repository is called by ViewModel`(): Unit = runBlocking {
        every { repository.searchMoviesBy(any()) } returns mockk()

        SUT.getMovies()
        verify(exactly = 1) { repository.searchMoviesBy(any()) }
    }

}