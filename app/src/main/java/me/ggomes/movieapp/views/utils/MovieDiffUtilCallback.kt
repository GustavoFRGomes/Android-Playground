package me.ggomes.movieapp.views.utils

import androidx.recyclerview.widget.DiffUtil
import me.ggomes.movieapp.models.Movie

class MovieDiffUtilCallback(
        private val oldMovieList: List<Movie>,
        private val newMovieList: List<Movie>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldMovieList.size

    override fun getNewListSize(): Int = newMovieList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovieList[oldItemPosition].id == newMovieList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldMovieList[oldItemPosition] == newMovieList[newItemPosition]
    }
}