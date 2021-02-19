package me.ggomes.movieapp.viewmodels

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import me.ggomes.movieapp.data.repositories.MovieRepository
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieDetailsViewModelTest {

    private lateinit var SUT: MovieDetailsViewModel
    private lateinit var repository: MovieRepository
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        repository = mockk()
        SUT = MovieDetailsViewModel(repository)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getMovieDetailsBy(): Unit = runBlocking {
        every { repository.getMovieBy(("testID")) } returns mockk()

        SUT.getMovieDetailsBy("testID")
        verify(exactly = 1) { repository.getMovieBy("testID") }
    }
}