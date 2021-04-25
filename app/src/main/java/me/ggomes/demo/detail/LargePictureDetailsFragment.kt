package me.ggomes.demo.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import me.ggomes.demo.common.views.fragments.LargePictureDetailsFragmentArgs
import me.ggomes.demo.databinding.FragmentLargePictureDetailsBinding
import me.ggomes.demo.detail.viewmodel.LargePictureViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LargePictureDetailsFragment: Fragment() {

    private lateinit var viewBinding: FragmentLargePictureDetailsBinding
    private val viewModel: LargePictureViewModel by viewModel()
    private val args : LargePictureDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentLargePictureDetailsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.largeImageUrl.let {
            Glide.with(view.context)
                .load(viewModel.generateImageUrlFromUri(it))
                .listener(getRequestListener())
                .into(viewBinding.showcaseLargeImageView)
        }
    }

    private fun getRequestListener(): RequestListener<Drawable?> {
        return object: RequestListener<Drawable?> {
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable?>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                viewBinding.progressBar.visibility = View.GONE
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable?>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
        }
    }
}