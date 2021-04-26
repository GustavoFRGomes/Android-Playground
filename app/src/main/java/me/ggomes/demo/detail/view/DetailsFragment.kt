package me.ggomes.demo.detail.view

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
import me.ggomes.demo.databinding.FragmentDetailsBinding
import me.ggomes.demo.detail.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment: Fragment() {

    private var _detailsViewBinding: FragmentDetailsBinding? = null
    private val detailsViewBinding get() = _detailsViewBinding!!

    private val viewModel: DetailsViewModel by viewModel()
    private val args : DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _detailsViewBinding = FragmentDetailsBinding.inflate(inflater)
        return detailsViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.imageUri.let {
            Glide.with(view.context)
                .load(viewModel.getImageUrlFromUri(it))
                .listener(getRequestListener())
                .into(detailsViewBinding.showcaseLargeImageView)
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
                detailsViewBinding.progressBar.visibility = View.GONE
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

    override fun onDestroyView() {
        super.onDestroyView()
        _detailsViewBinding = null
    }
}