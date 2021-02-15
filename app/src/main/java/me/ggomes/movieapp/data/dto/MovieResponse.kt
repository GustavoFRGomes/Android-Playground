package me.ggomes.movieapp.data.dto

import com.google.gson.annotations.SerializedName

// Example request: http://www.omdbapi.com/?apikey=50e4bb0e&type=movie&y=2020&page=1&s=dog
data class MovieResponse(
    @SerializedName("Search") val search: SearchResponse, // Search
    val totalResults: String,
    @SerializedName("Response") val response: String, // Response - can be "true" or "false"
)
