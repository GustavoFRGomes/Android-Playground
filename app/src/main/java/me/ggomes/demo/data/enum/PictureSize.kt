package me.ggomes.demo.data.enum

/**
 * Enum that represents both picture sizes required and the string that needs to be prepended to URI
 * URI of the image.
 */
enum class PictureSize(val appendValue: String) {
    LARGE("_27.jpg"),
    THUMBNAIL("_2.jpg")
}