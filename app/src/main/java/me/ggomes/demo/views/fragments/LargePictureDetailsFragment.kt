package me.ggomes.demo.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import me.ggomes.demo.databinding.FragmentMovieDetailsBinding

class LargePictureDetailsFragment: Fragment() {

    private lateinit var viewBinding: FragmentMovieDetailsBinding
    private val args : LargePictureDetailsFragmentArgs by navArgs()

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

        args.largeImageUrl.let {
            // TODO: Add error case if URL is bad.
            Glide.with(view.context)
                .load(it)
                .into(viewBinding.moviePosterImageview)
        }
    }
}