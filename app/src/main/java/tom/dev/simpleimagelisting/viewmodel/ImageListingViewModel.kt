package tom.dev.simpleimagelisting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import androidx.paging.toObservable
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import tom.dev.simpleimagelisting.model.datasource.ImageDataSourceFactory
import tom.dev.simpleimagelisting.model.dto.ImageResponseDocument
import tom.dev.simpleimagelisting.model.retrofit.ImageRetrofitService
import javax.inject.Inject

@HiltViewModel
class ImageListingViewModel
@Inject
constructor(
    private val imageRetrofitService: ImageRetrofitService
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun findImage(key: String, query: String): LiveData<PagedList<ImageResponseDocument>> {
        val imageDataSourceFactory =
            ImageDataSourceFactory(key, query, compositeDisposable, imageRetrofitService)

        return imageDataSourceFactory.toLiveData(80)
    }
}