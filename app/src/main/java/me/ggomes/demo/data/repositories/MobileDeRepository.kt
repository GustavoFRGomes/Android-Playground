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
    /**
     * Method to get the vehicle response from the network endpoint.
     *
     * @param vehicleId ID of the vehicle that will populate the gallery.
     */
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

    /**
     * Method to get the proper URL for the required size.
     *
     * @param vehicleUri URI of the vehicle that comes in the response from the endpoint
     * @param size it is the picture size
     * @return URL String for image fetching.
     */
    fun generateUrl(vehicleUri: String, size: PictureSize): String {
        val image = Image(vehicleUri)
        return when(size) {
            PictureSize.LARGE -> image.largeUrl
            PictureSize.THUMBNAIL -> image.thumbnailUrl
        }
    }
}