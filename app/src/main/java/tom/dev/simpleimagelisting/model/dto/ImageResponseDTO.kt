package tom.dev.simpleimagelisting.model.dto

data class ImageResponseDTO(
    val meta: ImageResponseMeta,
    val documents: List<ImageResponseDocument>
)