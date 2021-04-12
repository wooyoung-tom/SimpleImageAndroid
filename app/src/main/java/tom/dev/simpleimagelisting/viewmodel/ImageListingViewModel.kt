package tom.dev.simpleimagelisting.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tom.dev.simpleimagelisting.model.dto.ImageResponseDocument
import tom.dev.simpleimagelisting.model.dto.NetworkResult
import tom.dev.simpleimagelisting.model.retrofit.ImageRetrofitService
import javax.inject.Inject

@HiltViewModel
class ImageListingViewModel
@Inject
constructor(
    private val imageRetrofitService: ImageRetrofitService
) : ViewModel() {

    private val _imageSearchResultLiveData: MutableLiveData<List<ImageResponseDocument>> = MutableLiveData()
    val imageSearchResultLiveData: LiveData<List<ImageResponseDocument>> get() = _imageSearchResultLiveData

    private val _imageSearchFailedLiveData: MutableLiveData<String> = MutableLiveData()
    val imageSearchFailedLiveData: LiveData<String> get() = _imageSearchFailedLiveData

    fun findImage(key: String, query: String) {
        viewModelScope.launch {
            val result = try {
                val body = imageRetrofitService.findImages(key, query)

                NetworkResult.OK(body)
            } catch (e: Exception) {
                NetworkResult.Error(e)
            }

            when (result) {
                is NetworkResult.OK -> {
                    _imageSearchResultLiveData.postValue(result.data.documents)
                }
                is NetworkResult.Error -> {
                    _imageSearchFailedLiveData.postValue(result.e.message)
                }
            }
        }
    }
}