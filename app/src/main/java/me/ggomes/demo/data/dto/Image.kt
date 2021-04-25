package me.ggomes.demo.data.dto

data class Image(
    val uri: String
) {
    val thumbnailUrl : String
        get() = "https://${uri}_2.jpg"

    val largeUrl: String
        get() = "https://${uri}_27.jpg"
}
