package me.ggomes.demo.gallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.ggomes.demo.databinding.FragmentGalleryBinding
import me.ggomes.demo.gallery.models.GalleryImage
import me.ggomes.demo.gallery.viewmodel.GalleryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GalleryFragment: Fragment() {

    private var _galleryViewBinding: FragmentGalleryBinding? = null
    private val galleryViewBinding get() = _galleryViewBinding!!

    private val galleryViewModel: GalleryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _galleryViewBinding = FragmentGalleryBinding.inflate(inflater , container, false)
        return galleryViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = galleryViewBinding.galleryRecyclerview
        recycler.layoutManager = GridLayoutManager(
            requireContext(),
            2,
            RecyclerView.VERTICAL,
            false)

        galleryViewModel.errorLiveData.observe(viewLifecycleOwner) {
            presentErrorToast(it)
        }

        galleryViewBinding.progressBar.visibility = View.VISIBLE
        galleryViewModel.fetchVehicleById()

        galleryViewModel.carImagesLiveData.observe(viewLifecycleOwner) {
            galleryViewBinding.progressBar.visibility = View.GONE
            val recyclerAdapter = GalleryGridAdapter(it, ::navigateToDetailsScreen)
            recycler.adapter = recyclerAdapter
        }
    }

    private fun navigateToDetailsScreen(galleryImage: GalleryImage) {
        val action =
            GalleryFragmentDirections.actionGalleryFragmentToDetailsFragment(
                galleryImage.uri
            )

        findNavController().navigate(action)
    }

    private fun presentErrorToast(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _galleryViewBinding = null
    }
}