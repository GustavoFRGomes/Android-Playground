package me.ggomes.demo.data.repositories

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.ggomes.demo.data.database.MovieDatabase
import me.ggomes.demo.data.dto.VehicleDetailsResponse
import me.ggomes.demo.data.network.MobileDeApiService
import retrofit2.HttpException

class MobileDeRepository(
    private val apiService: MobileDeApiService
) {
    fun getVehicleById(vehicleId: Long): Flow<VehicleDetailsResponse> = flow {
        try {
            val networkResponse = apiService.getVehicleById(vehicleId)
            emit(networkResponse)
        } catch (exception: HttpException) {
            // Throw exception only if Database didn't provide results as well
            //if (databaseResult == null)
            //    throw exception
            Log.e("REPOSITORY", "Exception: ${exception.message()}")
        }
    }
}