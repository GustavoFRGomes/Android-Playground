package me.ggomes.movieapp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import me.ggomes.movieapp.R
import me.ggomes.movieapp.databinding.FragmentMovieDetailsBinding
import me.ggomes.movieapp.viewmodels.MovieDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment: Fragment() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel()
    private lateinit var viewBinding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMovieDetailsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            val id = bundle.getString(MOVIE_ID_KEY, "")

            viewBinding.progressBar.visibility = View.VISIBLE
            movieDetailsViewModel.getMovieDetailsBy(id).observe(viewLifecycleOwner) { movie ->
                Picasso.get()
                        .load(movie.posterUrl)
                        .into(viewBinding.moviePosterImageview)

                viewBinding.movieTitleTextview.text = movie.title
                viewBinding.movieYearTextview.text = movie.year

                viewBinding.movieActorsTextview.text = requireContext()
                    .getString(R.string.details_actors_dynamic, movie.actors)
                viewBinding.movieDirectorsTextview.text = requireContext()
                    .getString(R.string.details_directors_dynamic, movie.directors)

                viewBinding.moviePlotTextview.text = movie.plot

                viewBinding.progressBar.visibility = View.GONE
            }
        }

        movieDetailsViewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(
                context,
                requireContext().getString(R.string.error_data_retrieval),
                Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val MOVIE_ID_KEY = "movieId"
    }
}