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

    private val dtoToVehicleMapper : ImageDtoToVehicleMapper by inject(
        ImageDtoToVehicleMapper::class.java)

    private val errorHandler: ErrorHandler by inject(ErrorHandler::class.java)

    /**
     * Method to fetch the images of the vehicle by ID
     *
     * @param vehicleId ID of the vehicle that will be displayed (defaults to demo ID of 306863282)
     */
    fun fetchVehicleById(vehicleId: Long = 306863282) {
        viewModelScope.launch {
            mobileDeRepository.getVehicleImagesById(vehicleId)
                .collect {
                    try {
                        val images = it.map { dtoToVehicleMapper.mapFromImageDto(it) }
                        _carImagesLiveData.postValue(images)
                    } catch (exception: Exception) {
                        _errorLiveData.postValue(errorHandler.handleException(exception))
                    }
                }
        }
    }
}