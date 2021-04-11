package tom.dev.simpleimagelisting.view

import androidx.recyclerview.widget.RecyclerView
import coil.load
import tom.dev.simpleimagelisting.databinding.ItemThumbnailBinding
import tom.dev.simpleimagelisting.model.dto.ImageResponseDocument

class ImageListingViewHolder(
    private val binding: ItemThumbnailBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageResponseDocument) {
        binding.imageItemThumbnail.load(item.thumbnailUrl)
    }
}