package me.ggomes.movieapp.data.dto

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("Title") val title: String, // Title
    @SerializedName("Year") val year: String, // Year
    @SerializedName("imdbID") val imdbId: String, // imdbID
    @SerializedName("Type") val type: String, // Type
    @SerializedName("Poster") val posterUrl: String, // Poster
)