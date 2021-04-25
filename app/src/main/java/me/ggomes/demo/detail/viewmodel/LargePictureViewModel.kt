package me.ggomes.demo.detail.viewmodel

import androidx.lifecycle.ViewModel
import me.ggomes.demo.data.dto.Image

class LargePictureViewModel: ViewModel() {

    fun generateImageUrlFromUri(imageUri: String): String? {
        return Image(imageUri).largeUrl
    }
}