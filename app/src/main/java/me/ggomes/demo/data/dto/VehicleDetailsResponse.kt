package me.ggomes.demo.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Data class that represents the response from the endpoint.
 *
 * Example request: https://m.mobile.de/svc/a/306863282
 */
data class VehicleDetailsResponse(
    @SerializedName("makeKey") val maker: String,
    val id: Long,
    val url: String,
    val images: List<Image>?
)