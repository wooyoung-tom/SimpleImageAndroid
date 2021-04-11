package tom.dev.simpleimagelisting.model.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tom.dev.simpleimagelisting.model.retrofit.ImageRetrofitService
import tom.dev.simpleimagelisting.model.dto.ImageResponseDocument
import javax.inject.Inject

class ImageDataSource
@Inject
constructor(
    private val key: String,
    private val query: String,
    private val compositeDisposable: CompositeDisposable,
    private val imageRetrofitService: ImageRetrofitService
) : PageKeyedDataSource<Int, ImageResponseDocument>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ImageResponseDocument>
    ) {
        val firstPage = 1

        compositeDisposable.add(
            imageRetrofitService.findImages(key, query, firstPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("OK?", "$it")
                    callback.onResult(
                        it.documents,
                        firstPage,
                        it.meta.pageableCount,
                        null,
                        if (it.meta.isEnd) null else firstPage + 1,
                    )
                }, {
                    it.printStackTrace()
                })
        )
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ImageResponseDocument>
    ) {
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ImageResponseDocument>
    ) {
        compositeDisposable.add(
            imageRetrofitService.findImages(key, query, params.key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("OK?", "$it")
                    callback.onResult(
                        it.documents,
                        params.key + 1
                    )
                }, {
                    it.printStackTrace()
                })
        )
    }
}