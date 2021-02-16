package me.ggomes.movieapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    val year: String,
    val posterUrl: String
)
