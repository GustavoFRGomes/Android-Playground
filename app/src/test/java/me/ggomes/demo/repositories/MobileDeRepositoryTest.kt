package me.ggomes.demo.repositories

import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.*
import me.ggomes.demo.data.database.MovieDatabase
import me.ggomes.demo.data.network.MobileDeApiService
import me.ggomes.demo.data.repositories.MobileDeRepository
import org.junit.After
import org.junit.Before
import org.junit.Test

class MobileDeRepositoryTest {

    private lateinit var SUT: MobileDeRepository
    private val api = mockk<MobileDeApiService>(relaxed = true)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        SUT = MobileDeRepository(api)
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
    fun getMovieBy() {

        coEvery {
            runBlockingTest {  api.getVehicleById(1) }
        } returns mockk(relaxed = true)

        runBlockingTest {
            SUT.getVehicleById(1).collect()
        }

        coVerifyOrder {
            api.getVehicleById(1)
        }
    }
}