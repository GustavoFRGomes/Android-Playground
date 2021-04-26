package me.ggomes.demo.gallery.viewmodel

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import me.ggomes.demo.data.repositories.MobileDeRepository
import org.junit.Before
import org.junit.Test

class GalleryViewModelTest {

    private lateinit var SUT: GalleryViewModel
    private lateinit var repository: MobileDeRepository
    @ExperimentalCoroutinesApi
    private val coroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        SUT = GalleryViewModel(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `verify repository is called by ViewModel`() {
        coEvery { repository.getVehicleImagesById(any()) } returns mockk()

        coroutineDispatcher.runBlockingTest {
            SUT.fetchVehicleById()
        }

        coVerify(exactly = 1) { repository.getVehicleImagesById(any()) }
    }

}