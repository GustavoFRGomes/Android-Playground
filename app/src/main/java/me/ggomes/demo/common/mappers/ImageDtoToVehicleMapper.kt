package me.ggomes.demo.common.mappers

import me.ggomes.demo.data.dto.Image
import me.ggomes.demo.gallery.models.GalleryImage

class ImageDtoToVehicleMapper {
    fun mapFromImageDto(image: Image): GalleryImage {
        return GalleryImage(image.uri, image.largeUrl, image.thumbnailUrl)
    }
}