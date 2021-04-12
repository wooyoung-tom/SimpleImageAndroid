package tom.dev.simpleimagelisting.model.repository

import tom.dev.simpleimagelisting.model.dto.ImageResponseDTO

interface ImageRepository {
    suspend fun findImages(key: String, query: String): ImageResponseDTO
}