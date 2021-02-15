package me.ggomes.movieapp.data.dto

import com.google.gson.annotations.SerializedName

// Example request: http://www.omdbapi.com/?apikey=50e4bb0e&i=tt3418854
data class MovieDetailsResponse(
    @SerializedName("Title") val title: String, // Title
    @SerializedName("Year") val year: String, // Year
    @SerializedName("Rated") val ageRating: String, // Rated
    @SerializedName("Runtime") val runtime: String, // Runtime
    @SerializedName("Genre") val genres: String, // Genre
    @SerializedName("Director") val director: String, // Director
    @SerializedName("Actors") val actors: String, // Actors
    @SerializedName("Plot") val plot: String, // Plot
    @SerializedName("Language") val language: String, // Language
    @SerializedName("Poster") val posterUrl: String, // Poster
)