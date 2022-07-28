package com.wtt.composetest.repository.bean


/**
 * Created by Wangzhan on 2022/5/6
 *
 * @descr
 */
sealed class Result<out T> {
    data class Success<out T>(val data: T?) : Result<T>()

    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}