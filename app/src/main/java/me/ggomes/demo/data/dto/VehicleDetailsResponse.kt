package me.ggomes.demo.data.dto

import com.google.gson.annotations.SerializedName

// Example request: https://m.mobile.de/svc/a/306863282
data class VehicleDetailsResponse(
    @SerializedName("makeKey") val maker: String,
    val id: Long,
    val url: String,
    //@SerializedName("attributes") val attributesRawString: String,
    val price: Price?,
    val images: List<Image>?
)