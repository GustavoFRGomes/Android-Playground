package me.ggomes.demo.detail.viewmodel

import androidx.lifecycle.ViewModel
import me.ggomes.demo.data.enum.PictureSize
import me.ggomes.demo.data.repositories.MobileDeRepository

class LargePictureViewModel(
    private val repository: MobileDeRepository
): ViewModel() {

    fun generateImageUrlFromUri(imageUri: String): String {
        return repository.generateUrl(imageUri, PictureSize.LARGE)
    }
}