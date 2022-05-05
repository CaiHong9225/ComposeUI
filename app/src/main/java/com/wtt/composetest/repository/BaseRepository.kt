package com.wtt.composetest.repository

import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> WanResponse<T>): WanResponse<T> {
        return call.invoke()
    }

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> com.wtt.composetest.repository.bean.Result<T>,
        errorMessage: String
    ): com.wtt.composetest.repository.bean.Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            // An exception was thrown when calling the API so we're converting this to an IOException
            com.wtt.composetest.repository.bean.Result.Error(IOException(errorMessage, e))
        }
    }
}

class WanResponse<T> {

}
