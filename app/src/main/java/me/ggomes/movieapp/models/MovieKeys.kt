package me.ggomes.movieapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieKeys(
    @PrimaryKey(autoGenerate = true)
    val id: Int
)