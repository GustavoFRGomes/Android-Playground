package me.ggomes.movieapp.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import me.ggomes.movieapp.data.dao.MovieDao
import me.ggomes.movieapp.data.database.MovieDatabase
import me.ggomes.movieapp.data.network.OpenMovieDatabaseService
import me.ggomes.movieapp.data.network.RetrofitApiClient
import me.ggomes.movieapp.data.repositories.MovieRepository
import me.ggomes.movieapp.models.Movie
import me.ggomes.movieapp.models.MovieDetail
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class MovieRepositoryTest {

    private lateinit var SUT: MovieRepository
    private val db = mockk<MovieDatabase>(relaxed = true)
    private val api = mockk<OpenMovieDatabaseService>(relaxed = true)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        SUT = MovieRepository(db, api)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    /*
    @ExperimentalPagingApi
    @Test
    fun searchMoviesBy() = runBlocking {
        coEvery { db.movieDao().searchBy(any()) } returns mockk()
        coEvery { api.search(any()) } returns mockk()

        runBlocking {  SUT.searchMoviesBy("term").first() }

        //coVerify(atLeast = 1) { db.movieDao().searchBy(any()) }
        coVerify(atLeast = 1) { api.search(any(), any()) }
    }
    */

    @ExperimentalCoroutinesApi
    @Test
    fun getMovieBy() {
        coEvery {
            db.movieDetailDao().getDetailsBy("testId")
        } returns mockk(relaxed = true)

        coEvery {
            runBlockingTest {  api.getMovieBy("testId") }
        } returns mockk(relaxed = true)

        runBlockingTest {
            SUT.getMovieBy("testId").collect()
        }

        coVerifyOrder {
            db.movieDetailDao().getDetailsBy("testId")
            api.getMovieBy("testId")
        }
    }
}