package me.ggomes.demo.viewmodels

import androidx.paging.ExperimentalPagingApi
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import me.ggomes.demo.data.repositories.MobileDeRepository
import org.junit.After
import org.junit.Test

import org.junit.Before

class VehicleListViewModelTest {

    private lateinit var SUT: VehicleListViewModel
    private lateinit var repository: MobileDeRepository
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        repository = mockk()
        SUT = VehicleListViewModel(repository)
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
        every { repository.getVehicleById(any()) } returns mockk()

        SUT.getVehicleById()
        verify(exactly = 1) { repository.getVehicleById(any()) }
    }

}