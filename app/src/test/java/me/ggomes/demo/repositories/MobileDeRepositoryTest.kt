package me.ggomes.demo.repositories

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import me.ggomes.demo.data.dto.VehicleDetailsResponse
import me.ggomes.demo.data.network.MobileDeApiService
import me.ggomes.demo.data.repositories.MobileDeRepository
import org.junit.After
import org.junit.Before
import org.junit.Test

class MobileDeRepositoryTest {

    private lateinit var SUT: MobileDeRepository
    private val api = mockk<MobileDeApiService>(relaxed = true)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val mockResponse = VehicleDetailsResponse(
        "ferrari",
        1,
        "URL",
        emptyList())

    @ExperimentalCoroutinesApi
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
    fun getVehicleById() {

        coEvery {
            api.getVehicleById(1)
        } returns mockResponse

        runBlockingTest {
            SUT.getVehicleImagesById(1).collect()
        }

        coVerify(exactly = 1) {
            api.getVehicleById(1)
        }
    }
}