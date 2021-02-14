package me.ggomes.movieapp.data.dto

// Example request: http://www.omdbapi.com/?apikey=50e4bb0e&type=movie&y=2020&page=1&s=dog
data class MovieResponse(
    val search: SearchResponse, // Search
    val totalResults: String,
    val response: String, // Response - can be "true" or "false"
)
