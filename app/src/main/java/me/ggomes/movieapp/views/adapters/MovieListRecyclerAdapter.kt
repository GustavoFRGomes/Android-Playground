package me.ggomes.movieapp.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import me.ggomes.movieapp.R
import me.ggomes.movieapp.models.Movie
import me.ggomes.movieapp.views.utils.MovieDiffUtilCallback

class MovieListRecyclerAdapter(
    private val onItemClickListener: (Movie) -> Unit
): RecyclerView.Adapter<MovieListRecyclerAdapter.MovieViewHolder>() {

    private val movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie_item, parent, false)

        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    fun updatedMovieList(updatedMovieList: List<Movie>) {
        val diffUtilCallback = MovieDiffUtilCallback(movies, updatedMovieList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        movies.clear()
        movies.addAll(updatedMovieList)

        diffResult.dispatchUpdatesTo(this)

    }

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            val posterImageView = itemView.findViewById<ImageView>(R.id.movie_poster_imageview)
            Picasso.get()
                    .load(movie.posterUrl)
                    .into(posterImageView)

            itemView.findViewById<TextView>(R.id.movie_title_textview).text = movie.title
            itemView.findViewById<TextView>(R.id.movie_year_textview).text = movie.year

            itemView.setOnClickListener {
                onItemClickListener(movie)
            }
        }
    }

}