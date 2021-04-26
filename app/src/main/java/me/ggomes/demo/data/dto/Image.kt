package me.ggomes.demo.data.dto

import me.ggomes.demo.data.enum.PictureSize

/**
 * Image class that represents each image from the endpoint response JSON
 */
data class Image(
    val uri: String
) {
    /**
     * Computed property to transform the URI into a proper image URL for the thumbnail size
     */
    val thumbnailUrl : String
        get() = "https://${uri + PictureSize.THUMBNAIL.appendValue}"

    /**
     * Computed property to transform the URI into a proper image URL for the large size
     */
    val largeUrl: String
        get() = "https://${uri + PictureSize.LARGE.appendValue}"
}
