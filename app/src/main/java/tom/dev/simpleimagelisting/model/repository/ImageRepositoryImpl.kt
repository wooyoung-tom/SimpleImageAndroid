package tom.dev.simpleimagelisting.model.repository

import tom.dev.simpleimagelisting.model.dto.ImageResponseDTO
import tom.dev.simpleimagelisting.model.retrofit.ImageRetrofitService
import javax.inject.Inject

class ImageRepositoryImpl
@Inject
constructor(
    private val imageRetrofitService: ImageRetrofitService
) : ImageRepository {
    override suspend fun findImages(key: String, query: String): ImageResponseDTO {
        return imageRetrofitService.findImages(key, query)
    }
}