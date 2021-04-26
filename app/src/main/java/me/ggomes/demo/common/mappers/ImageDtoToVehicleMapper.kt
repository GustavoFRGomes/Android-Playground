package me.ggomes.demo.common.mappers

import me.ggomes.demo.data.dto.Image
import me.ggomes.demo.gallery.models.GalleryImage

/**
 * Helper class that will map from the Image data class into a model that is used by the views
 */
class ImageDtoToVehicleMapper {
    fun mapFromImageDto(image: Image): GalleryImage {
        return GalleryImage(image.uri, image.largeUrl, image.thumbnailUrl)
    }
}