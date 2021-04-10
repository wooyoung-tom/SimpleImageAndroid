package tom.dev.simpleimagelisting.model.dto

import com.google.gson.annotations.SerializedName

data class ImageResponseDocument(

    val collection: String,

    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,

    @SerializedName("image_url")
    val imageUrl: String,

    val width: Int,

    val height: Int,

    @SerializedName("display_sitename")
    val displaySiteName: String,

    @SerializedName("doc_url")
    val docUrl: String,

    val datetime: String
)