package me.ggomes.demo.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.ggomes.demo.R
import me.ggomes.demo.data.dto.Image
import me.ggomes.demo.databinding.ViewHolderMovieItemBinding

class VehicleGridRecyclerAdapter(
    private val imageUrls: List<Image>,
    private val onItemClickListener: (Image) -> Unit
): RecyclerView.Adapter<VehicleGridRecyclerAdapter.VehicleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val itemView = ViewHolderMovieItemBinding.inflate(
            LayoutInflater.from(parent.context)
        )

        return VehicleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(
            imageUrls[position]
        )
    }

    override fun getItemCount() = imageUrls.size

    inner class VehicleViewHolder(
        private val viewBinding: ViewHolderMovieItemBinding
    ): RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(image: Image) {
            Glide.with(this.viewBinding.root.context)
                .load(image.thumbnailUrl)
                .placeholder(R.drawable.movie_placeholder)
                .into(viewBinding.moviePosterImageview)

            itemView.setOnClickListener {
                onItemClickListener(image)
            }
        }
    }

}