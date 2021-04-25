package me.ggomes.demo.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = Movie::class,
    parentColumns = ["id"],
    childColumns = ["id"],
    onDelete = ForeignKey.CASCADE)])
data class MovieDetail(
    @PrimaryKey val id: String,
    val title: String,
    val posterUrl: String,
    val plot: String,
    val year: String,
    val actors: String,
    val directors: String,
)
