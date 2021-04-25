package me.ggomes.demo.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieKeys(
    @PrimaryKey(autoGenerate = true)
    val id: Int
)