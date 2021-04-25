package me.ggomes.demo.gallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.ggomes.demo.R
import me.ggomes.demo.data.dto.Image
import me.ggomes.demo.gallery.viewmodel.VehicleListViewModel
import me.ggomes.demo.common.views.fragments.VehicleListFragmentDirections
import me.ggomes.demo.databinding.FragmentVehicleGalleryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class VehicleGalleryFragment: Fragment() {

    private val vehicleListViewModel: VehicleListViewModel by viewModel()
    private lateinit var galleryViewBinding: FragmentVehicleGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        galleryViewBinding = FragmentVehicleGalleryBinding.inflate(inflater , container, false)
        return galleryViewBinding.root
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = galleryViewBinding.galleryRecyclerview
        recycler.layoutManager = GridLayoutManager(
            requireContext(),
            2,
            RecyclerView.VERTICAL,
            false)
        //recycler.setHasFixedSize(true)

        vehicleListViewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(
                context,
                requireContext().getString(R.string.error_data_retrieval),
                Toast.LENGTH_SHORT).show()
        }

        galleryViewBinding.progressBar.visibility = View.VISIBLE
        vehicleListViewModel.getVehicleById()

        vehicleListViewModel.carImagesLiveData.observe(viewLifecycleOwner) {
            galleryViewBinding.progressBar.visibility = View.GONE
            val recyclerAdapter = VehicleGridRecyclerAdapter(it, ::navigateToMovieDetails)
            recycler.adapter = recyclerAdapter
        }
    }

    private fun navigateToMovieDetails(image: Image) {
        val action =
            VehicleListFragmentDirections.actionVehicleListFragmentToLargePictureDetailsFragment(
                image.largeUrl!!
            )

        findNavController().navigate(action)
    }
}