package me.ggomes.demo.gallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import me.ggomes.demo.common.ErrorHandler
import me.ggomes.demo.common.mappers.ImageDtoToVehicleMapper
import me.ggomes.demo.common.viewmodels.BaseViewModel
import me.ggomes.demo.data.repositories.MobileDeRepository
import me.ggomes.demo.gallery.models.GalleryImage
import org.koin.java.KoinJavaComponent.inject

class GalleryViewModel(
    private val mobileDeRepository: MobileDeRepository
): BaseViewModel() {

    private val _carImagesLiveData = MutableLiveData<List<GalleryImage>>()
    val carImagesLiveData: LiveData<List<GalleryImage>> = _carImagesLiveData

    private val dtoToVehicleMapper = ImageDtoToVehicleMapper()
    private val errorHandler: ErrorHandler by inject(ErrorHandler::class.java)

    // Using hardcoded default value as in the doc
    fun getVehicleById(vehicleId: Long = 306863282) {
        viewModelScope.launch {
            mobileDeRepository.getVehicleImagesById(vehicleId)
                .handleErrors()
                .collect {
                    val images = it.map { dtoToVehicleMapper.mapFromImageDto(it) }
                    _carImagesLiveData.postValue(images)
                }
        }
    }

    // Helper function to handle errors that might come from the the API or Parsing.
    private fun <T> Flow<T>.handleErrors(): Flow<T> = flow {
        try {
            collect { value -> emit(value)}
        } catch (exception: Exception) {
            _errorLiveData.postValue(errorHandler.handleException(exception))
        }
    }
}