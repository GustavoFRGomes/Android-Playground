package me.ggomes.demo.data.repositories

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.ggomes.demo.data.dto.Image
import me.ggomes.demo.data.dto.VehicleDetailsResponse
import me.ggomes.demo.data.enum.PictureSize
import me.ggomes.demo.data.network.MobileDeApiService
import retrofit2.HttpException

class MobileDeRepository(
    private val apiService: MobileDeApiService
) {
    fun getVehicleImagesById(vehicleId: Long): Flow<List<Image>> = flow {
        try {
            val networkResponse = apiService.getVehicleById(vehicleId)

            networkResponse.images?.let {
                emit(networkResponse.images)
            }
        } catch (exception: HttpException) {
            Log.e("REPOSITORY", "Exception: ${exception.message()}")
            throw exception
        }
    }

    fun generateUrl(vehicleUri: String, size: PictureSize): String {
        val image = Image(vehicleUri)
        return when(size) {
            PictureSize.LARGE -> image.largeUrl
            PictureSize.THUMBNAIL -> image.thumbnailUrl
        }
    }
}