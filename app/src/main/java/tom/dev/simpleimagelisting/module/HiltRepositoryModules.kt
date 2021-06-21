package tom.dev.simpleimagelisting.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import tom.dev.simpleimagelisting.model.repository.ImageRepository
import tom.dev.simpleimagelisting.model.repository.ImageRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class HiltRepositoryModules {

    @Binds
    abstract fun bindImageRepository(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository


}