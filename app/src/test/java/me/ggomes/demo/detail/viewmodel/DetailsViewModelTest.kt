package me.ggomes.demo.detail.viewmodel

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import me.ggomes.demo.data.enum.PictureSize
import me.ggomes.demo.data.repositories.MobileDeRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailsViewModelTest {

    private lateinit var SUT: DetailsViewModel
    private lateinit var repository: MobileDeRepository

    @Before
    fun setup() {
        repository = mockk()
        SUT = DetailsViewModel(repository)
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