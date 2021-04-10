package tom.dev.simpleimagelisting.model.dto

import com.google.gson.annotations.SerializedName

data class ImageResponseMeta(

    @SerializedName("total_count")
    val totalCount: Long,

    @SerializedName("pageable_count")
    val pageableCount: Int,

    @SerializedName("is_end")
    val isEnd: Boolean
)