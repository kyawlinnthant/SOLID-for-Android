package mdy.klt.dependencyinjectionforandroid.core.common

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T> : Resource<T>()
    class Error<T>(errorMessage: String) : Resource<T>(null, errorMessage)
    class Success<T>(data: T) : Resource<T>(data)
}

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): Resource<T> {
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body() ?: return Resource.Error("EMPTY BODY")
            return Resource.Success(body)
        }
        return Resource.Error("ERROR CODE ${response.code()} : ${response.message()}")

    } catch (e: Exception) {
        return Resource.Error(e.message ?: "Something is wrong")
    } catch (e: IOException) {
        return Resource.Error(e.message ?: "Check your request")
    } catch (e: HttpException) {
        return Resource.Error(e.message ?: "Check Internet connection")
    }
}