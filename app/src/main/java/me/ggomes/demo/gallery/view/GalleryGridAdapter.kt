package me.ggomes.demo.gallery.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.ggomes.demo.R
import me.ggomes.demo.databinding.ViewHolderGalleryItemBinding
import me.ggomes.demo.gallery.models.GalleryImage

class GalleryGridAdapter(
    private val galleryImages: List<GalleryImage>,
    private val onItemClickListener: (GalleryImage) -> Unit
): RecyclerView.Adapter<GalleryGridAdapter.GalleryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemViewHolder {
        val itemView = ViewHolderGalleryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return GalleryItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GalleryItemViewHolder, position: Int) {
        holder.bind(
            galleryImages[position]
        )
    }

    override fun getItemCount() = galleryImages.size

    inner class GalleryItemViewHolder(
        private val viewBinding: ViewHolderGalleryItemBinding
    ): RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(galleryImage: GalleryImage) {
            Glide.with(this.viewBinding.root.context)
                .load(galleryImage.thumbnailImageUrl)
                .placeholder(R.drawable.gallery_item_placeholder)
                .into(viewBinding.itemImageView)

            itemView.setOnClickListener {
                onItemClickListener(galleryImage)
            }
        }
    }

}