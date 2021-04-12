package tom.dev.simpleimagelisting.view

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import tom.dev.simpleimagelisting.databinding.ItemThumbnailBinding
import tom.dev.simpleimagelisting.model.dto.ImageResponseDocument

class ImageListingViewHolder(
    private val binding: ItemThumbnailBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageResponseDocument) {
        binding.imageItemThumbnail.load(item.thumbnailUrl) {
            // Rounded Corner Radius 12.0f
            transformations(RoundedCornersTransformation(8f))
        }
    }
}