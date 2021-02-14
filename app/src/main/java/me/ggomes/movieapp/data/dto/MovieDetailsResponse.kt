package me.ggomes.movieapp.data.dto

// Example request: http://www.omdbapi.com/?apikey=50e4bb0e&i=tt3418854
data class MovieDetailsResponse(
    val title: String, // Title
    val year: String, // Year
    val ageRating: String, // Rated
    val runtime: String, // Runtime
    val genres: String, // Genre
    val director: String, // Director
    val actors: String, // Actors
    val plot: String, // Plot
    val language: String, // Language
    val posterUrl: String, // Poster
)