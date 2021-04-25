package me.ggomes.demo.data.dto

import org.junit.Test

import org.junit.Assert.*

class ImageModelTest {

    private val testImage = Image("URI")

    @Test
    fun getThumbnailUrl() {
        val expectedUri = "https://URI_2.jpg"
        assertEquals(testImage.thumbnailUrl, expectedUri)
    }

    @Test
    fun getLargeUrl() {
        val expectedUri = "https://URI_27.jpg"
        assertEquals(testImage.largeUrl, expectedUri)
    }
}