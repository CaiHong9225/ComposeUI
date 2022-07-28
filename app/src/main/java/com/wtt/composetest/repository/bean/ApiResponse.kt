package com.wtt.composetest.repository.bean


/**
 * Created by Wangzhan on 2022/5/6
 *
 * @descr
 */
private const val CODE_SUCCESS = 0

class ApiResponse<T> {
    var code: Int = -1
    var message: String? = null
    var data: T? = null
    fun isSuccess(): Boolean {
        return code == CODE_SUCCESS;
    }
}