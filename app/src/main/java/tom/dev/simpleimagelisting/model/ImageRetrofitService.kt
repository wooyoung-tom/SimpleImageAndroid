package tom.dev.simpleimagelisting.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import tom.dev.simpleimagelisting.model.dto.ImageResponseDTO

interface ImageRetrofitService {

    @GET("/v2/search/image")
    fun findImages(
        @Header("Authorization") key: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Single<ImageResponseDTO>
}