package com.wtt.composetest.repository

import com.wtt.composetest.api.ApiService
import com.wtt.composetest.repository.bean.ApiResponse
import com.wtt.composetest.repository.entity.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Created by Wangzhan on 2022/5/6
 *
 * @descr
 */
class UserRepository(private val apiService: ApiService) {

    suspend fun loadUserInfo(userId: String): ApiResponse<UserInfo> {
        return withContext(Dispatchers.IO) {
            apiService.loadUser(userId = userId)
        }
    }
}