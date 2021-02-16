package me.ggomes.movieapp.views.utils

import androidx.recyclerview.widget.DiffUtil
import me.ggomes.movieapp.models.Movie

class MovieDiffItemCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}