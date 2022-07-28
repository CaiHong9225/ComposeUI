package com.wtt.compose.net

import com.wtt.compose.entity.DemoReqData
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by Wangzhan on 2022/6/1
 *
 * @descr
 */
interface DataApi {
    /**
     * 获取数据
     */
    @GET("wenda/list/{pageId}/json")
    suspend fun getData(@Path("pageId") pageId:Int): DemoReqData
}