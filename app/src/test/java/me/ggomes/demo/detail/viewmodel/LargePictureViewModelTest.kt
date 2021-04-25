package me.ggomes.demo.detail.viewmodel

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import me.ggomes.demo.data.enum.PictureSize
import me.ggomes.demo.data.repositories.MobileDeRepository
import me.ggomes.demo.gallery.viewmodel.VehicleListViewModel
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class LargePictureViewModelTest {

    private lateinit var SUT: LargePictureViewModel
    private lateinit var repository: MobileDeRepository

    @Before
    fun setup() {
        repository = mockk()
        SUT = LargePictureViewModel(repository)
    }


    @Test
    fun `check if view model provides correct URL for URI`() {
        val testUri = "URI"
        val expectedUrl = "https://URI_27.jpg"
        every { repository.generateUrl(testUri, PictureSize.LARGE) } returns expectedUrl

        assertEquals(SUT.generateImageUrlFromUri(testUri), expectedUrl)
    }

    @Test
    fun `check if view model calls repository for Large URL`() {
        every { repository.generateUrl(any(), any()) } returns "URL"

        SUT.generateImageUrlFromUri("URI")
        verify(exactly = 1) { repository.generateUrl("URI", PictureSize.LARGE) }
    }
}