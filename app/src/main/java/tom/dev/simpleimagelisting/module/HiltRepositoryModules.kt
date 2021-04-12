package tom.dev.simpleimagelisting.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tom.dev.simpleimagelisting.model.repository.ImageRepository
import tom.dev.simpleimagelisting.model.repository.ImageRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltRepositoryModules {

    @Binds
    abstract fun bindImageRepository(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository
}