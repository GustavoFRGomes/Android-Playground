package me.ggomes.demo.detail.viewmodel

import androidx.lifecycle.ViewModel
import me.ggomes.demo.data.enum.PictureSize
import me.ggomes.demo.data.repositories.MobileDeRepository

class DetailsViewModel(
    private val repository: MobileDeRepository
): ViewModel() {

    /**
     * Method to convert an image URI into an URL
     *
     * @param imageUri Image URI String
     * @return Image URL String
     */
    fun getImageUrlFromUri(imageUri: String): String {
        return repository.generateUrl(imageUri, PictureSize.LARGE)
    }
}