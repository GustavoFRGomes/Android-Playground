package me.ggomes.movieapp.data.dto

data class SearchResponse(
    val title: String, // Title
    val year: String, // Year
    val imdbId: String, // imdbID
    val type: String, // Type
    val posterUrl: String, // Poster
)