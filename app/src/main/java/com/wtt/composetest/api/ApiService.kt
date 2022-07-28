package com.wtt.composetest.api

import com.wtt.composetest.repository.WanResponse
import com.wtt.composetest.repository.bean.ApiResponse
import com.wtt.composetest.repository.entity.UserInfo
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): WanResponse<User>


    @GET("/user/userinfo")
    suspend fun loadUser(@Query("userId") userId: String): ApiResponse<UserInfo>
}

class User {

}
