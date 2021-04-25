package me.ggomes.demo.data.network

import me.ggomes.demo.data.dto.VehicleDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MobileDeApiService {

    @GET("svc/a/{id}")
    suspend fun getVehicleById(@Path("id") id: Long): VehicleDetailsResponse
}