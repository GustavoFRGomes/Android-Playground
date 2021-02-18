package me.ggomes.movieapp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.ggomes.movieapp.R
import me.ggomes.movieapp.models.Movie
import me.ggomes.movieapp.viewmodels.MovieListViewModel
import me.ggomes.movieapp.views.adapters.MovieListRecyclerAdapter

class MovieListFragment: Fragment() {

    val movieListViewModel: MovieListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.movie_recyclerview)
        recycler.layoutManager = LinearLayoutManager(context!!, RecyclerView.VERTICAL, false)
        val recyclerAdapter = MovieListRecyclerAdapter(::navigateToMovieDetails)
        recycler.adapter = recyclerAdapter

        movieListViewModel.errorLiveData.observe(this) {
            Toast.makeText(context, context!!.getString(R.string.error_data_retrieval), Toast.LENGTH_SHORT)
                    .show()
        }

        lifecycleScope.launch {
            movieListViewModel.getMovies().collectLatest { pagingData ->
                recyclerAdapter.submitData(pagingData)
            }
        }

    }

    private fun navigateToMovieDetails(movie: Movie) {
        val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(movie.id)

        findNavController().navigate(action)
    }
}