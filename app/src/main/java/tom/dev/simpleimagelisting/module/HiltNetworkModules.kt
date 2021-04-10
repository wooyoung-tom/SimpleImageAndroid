package tom.dev.simpleimagelisting.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tom.dev.simpleimagelisting.model.ImageRetrofitService
import javax.inject.Singleton

/**
 * Modules Install In Singleton
 */
@Module
@InstallIn(SingletonComponent::class)
object HiltNetworkModules {

    // Kakao API base url
    private const val BASE_URL = "https://dapi.kakao.com"

    // Retrofit Build
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideImageRetrofitService(retrofit: Retrofit): ImageRetrofitService =
        retrofit.create(ImageRetrofitService::class.java)
}
