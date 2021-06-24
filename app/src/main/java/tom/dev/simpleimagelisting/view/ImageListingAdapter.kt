package tom.dev.simpleimagelisting.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import tom.dev.simpleimagelisting.databinding.ItemThumbnailBinding
import tom.dev.simpleimagelisting.model.dto.ImageResponseDocument

class ImageListingAdapter : ListAdapter<ImageResponseDocument, ImageListingViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<ImageResponseDocument>() {
        override fun areItemsTheSame(oldItem: ImageResponseDocument, newItem: ImageResponseDocument): Boolean {
            return oldItem.thumbnailUrl == newItem.thumbnailUrl
        }

        override fun areContentsTheSame(
            oldItem: ImageResponseDocument,
            newItem: ImageResponseDocument
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListingViewHolder {
        return ImageListingViewHolder(
            ItemThumbnailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImageListingViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }
}