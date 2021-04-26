package me.ggomes.demo.common.viewmodels.viewmodels

import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import me.ggomes.demo.data.repositories.MobileDeRepository
import me.ggomes.demo.gallery.viewmodel.VehicleListViewModel
import org.junit.After
import org.junit.Test

import org.junit.Before
import java.util.concurrent.Executors

class VehicleListViewModelTest {

    private lateinit var SUT: VehicleListViewModel
    private lateinit var repository: MobileDeRepository
    @ExperimentalCoroutinesApi
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        SUT = VehicleListViewModel(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `verify repository is called by ViewModel`() {
        coEvery { repository.getVehicleImagesById(any()) } returns mockk()

        coroutineDispatcher.runBlockingTest {
            SUT.getVehicleById()
        }

        coVerify(exactly = 1) { repository.getVehicleImagesById(any()) }
    }

}