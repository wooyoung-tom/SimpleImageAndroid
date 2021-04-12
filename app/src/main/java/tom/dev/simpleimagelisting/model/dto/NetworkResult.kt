package tom.dev.simpleimagelisting.model.dto

sealed class NetworkResult<out T> {

    data class OK<out T>(val data: T) : NetworkResult<T>()

    data class Error(val e: Throwable) : NetworkResult<Nothing>()
}
