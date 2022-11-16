package com.wtt.mykotlin.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Created by Wangzhan on 2022/2/11
 *
 * @descr
 */
class LoginRepository {

    /**
     * 切换到IO协程
     */
    suspend fun makeLoginRequest(jsonBody: String): String {
        return withContext(Dispatchers.IO) {
            "adadas"
        }
    }
}