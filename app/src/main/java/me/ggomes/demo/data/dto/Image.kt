package me.ggomes.demo.data.dto

import com.google.gson.annotations.SerializedName

data class Image(
    val uri: String?,
    val set: String?,
) {
    val thumbnailUrl : String?
        get() = uri?.let {
            "https://${it}_2.jpg"
        }

    val largeUrl: String?
        get() = uri?.let {
            "https://${it}_27.jpg"
        }
}
