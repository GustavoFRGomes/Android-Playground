package me.ggomes.demo.gallery.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.ggomes.demo.common.viewmodels.BaseViewModel
import me.ggomes.demo.data.dto.Image
import me.ggomes.demo.data.repositories.MobileDeRepository

class VehicleListViewModel(
    private val mobileDeRepository: MobileDeRepository
): BaseViewModel() {

    private val _carImagesLiveData = MutableLiveData<List<Image>>()
    val carImagesLiveData: LiveData<List<Image>> = _carImagesLiveData

    // Using hardcoded default value as in the doc
    fun getVehicleById(vehicleId: Long = 306863282) {
        viewModelScope.launch {
            mobileDeRepository.getVehicleImagesById(vehicleId)
                .collect {
                    _carImagesLiveData.postValue(it)
                }
        }
    }
}