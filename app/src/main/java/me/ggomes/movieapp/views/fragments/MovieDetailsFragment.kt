package me.ggomes.movieapp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import me.ggomes.movieapp.databinding.FragmentMovieDetailsBinding
import me.ggomes.movieapp.viewmodels.MovieDetailsViewModel

class MovieDetailsFragment: Fragment() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    private lateinit var viewBinding: FragmentMovieDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = FragmentMovieDetailsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            val id = bundle.getString(MOVIE_ID_KEY, "")

            movieDetailsViewModel.getMovieDetailsBy(id).observe(this) { movie ->
                Picasso.get()
                        .load(movie.posterUrl)
                        .into(viewBinding.moviePosterImageview)

                viewBinding.movieTitleTextview.text = movie.title
                viewBinding.movieYearTextview.text = movie.year
                viewBinding.movieActorsTextview.text = "Cast: ${movie.actors}"
                viewBinding.movieDirectorsTextview.text = "Directors: ${movie.directors}"
                viewBinding.moviePlotTextview.text = movie.plot
            }
        }

        movieDetailsViewModel.errorLiveData.observe(this) {
            Toast.makeText(context, "There was an error retrieving the detail for the movie!", Toast.LENGTH_SHORT)
                    .show()
        }
    }

    companion object {
        const val MOVIE_ID_KEY = "movieId"
    }
}