package me.ggomes.demo.common.mappers

import me.ggomes.demo.data.dto.Image
import org.junit.Test

import org.junit.Assert.*

class ImageDtoToVehicleMapperTest {
    private val STU = ImageDtoToVehicleMapper()

    @Test
    fun `verify mapper is correctly converting between models`() {
        val expectedImage = Image("URI")

        val resultingMapping = STU.mapFromImageDto(expectedImage)
        assertEquals(resultingMapping.largeImageUrl, expectedImage.largeUrl)
        assertEquals(resultingMapping.thumbnailImageUrl, expectedImage.thumbnailUrl)
        assertEquals(resultingMapping.uri, expectedImage.uri)
    }
}