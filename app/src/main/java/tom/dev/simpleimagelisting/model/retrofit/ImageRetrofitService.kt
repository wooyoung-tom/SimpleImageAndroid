package tom.dev.simpleimagelisting.model.retrofit

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import tom.dev.simpleimagelisting.model.dto.ImageResponseDTO

interface ImageRetrofitService {
    @GET("/v2/search/image")
    suspend fun findImages(
        @Header("Authorization") key: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): ImageResponseDTO
}