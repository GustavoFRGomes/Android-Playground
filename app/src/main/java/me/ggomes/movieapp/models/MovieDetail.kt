package me.ggomes.movieapp.models

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(foreignKeys = [ForeignKey(
    entity = Movie::class,
    parentColumns = ["id"],
    childColumns = ["id"],
    onDelete = ForeignKey.CASCADE)])
data class MovieDetail(
    val id: String,
    val title: String,
    val posterUrl: String,
    val plot: String,
)
