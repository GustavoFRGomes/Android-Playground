package me.ggomes.movieapp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.ggomes.movieapp.R
import me.ggomes.movieapp.databinding.FragmentMovieListBinding
import me.ggomes.movieapp.models.Movie
import me.ggomes.movieapp.viewmodels.MovieListViewModel
import me.ggomes.movieapp.views.adapters.MovieListRecyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment: Fragment() {

    private val movieListViewModel: MovieListViewModel by viewModel()
    private lateinit var movieListViewBinding: FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        movieListViewBinding = FragmentMovieListBinding.inflate(inflater , container, false)
        return movieListViewBinding.root
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = movieListViewBinding.movieRecyclerview
        recycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val recyclerAdapter = MovieListRecyclerAdapter(::navigateToMovieDetails)
        recycler.adapter = recyclerAdapter

        movieListViewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(
                context,
                requireContext().getString(R.string.error_data_retrieval),
                Toast.LENGTH_SHORT).show()
        }

        movieListViewBinding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            movieListViewModel.getMovies().collectLatest { pagingData ->
                movieListViewBinding.progressBar.visibility = View.GONE
                recyclerAdapter.submitData(pagingData)
            }
        }

    }

    private fun navigateToMovieDetails(movie: Movie) {
        val action = MovieListFragmentDirections
            .actionMovieListFragmentToMovieDetailsFragment(movie.id)

        findNavController().navigate(action)
    }
}