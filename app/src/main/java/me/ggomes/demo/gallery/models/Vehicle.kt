package me.ggomes.demo.gallery.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.ggomes.demo.data.dto.Image

data class Vehicle(
    val id: String,
    val largeImageUrl: String,
    val thumbnailImageUrl: String
)
