package com.wtt.compose.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Wangzhan on 2022/6/1
 *
 * @descr
 */
object RetrofitService {

    /**
     * okhttp client
     */
    lateinit var okHttpClient: OkHttpClient

    /**
     * 主Url地址
     */
    private const val BASEAPI = "https://www.wanandroid.com/";


    /**
     * 创建service对象
     */
    fun <T> createService(mClass: Class<T>): T {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder();
        okHttpClient = builder.build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASEAPI)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(mClass) as T
    }

}