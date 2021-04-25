package me.ggomes.demo.gallery.viewmodel

import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import me.ggomes.demo.common.viewmodels.BaseViewModel
import me.ggomes.demo.data.dto.Image
import me.ggomes.demo.data.repositories.MobileDeRepository

class VehicleListViewModel(
    private val mobileDeRepository: MobileDeRepository
): BaseViewModel() {

    private val _carImagesLiveData = MutableLiveData<List<Image>>()
    val carImagesLiveData: LiveData<List<Image>> = _carImagesLiveData

    @ExperimentalPagingApi
    fun getVehicleById(vehicleId: Long = 306863282) {
        mobileDeRepository.getVehicleById(vehicleId).asLiveData().observeForever {
            _carImagesLiveData.postValue(it.images)
        }
    }
}