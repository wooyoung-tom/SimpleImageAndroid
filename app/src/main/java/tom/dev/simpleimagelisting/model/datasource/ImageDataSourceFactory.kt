package tom.dev.simpleimagelisting.model.datasource

import android.util.Log
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import tom.dev.simpleimagelisting.model.dto.ImageResponseDocument
import tom.dev.simpleimagelisting.model.retrofit.ImageRetrofitService
import javax.inject.Inject

class ImageDataSourceFactory
@Inject
constructor(
    private val key: String,
    private val query: String,
    private val compositeDisposable: CompositeDisposable,
    private val imageRetrofitService: ImageRetrofitService
) : DataSource.Factory<Int, ImageResponseDocument>() {

    override fun create(): DataSource<Int, ImageResponseDocument> {
        Log.d("Factory", "Creation")
        return ImageDataSource(key, query, compositeDisposable, imageRetrofitService)
    }
}