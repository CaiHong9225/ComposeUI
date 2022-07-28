package com.wtt.compose.repository

import com.wtt.compose.entity.DemoReqData
import com.wtt.compose.net.DataApi
import com.wtt.compose.net.RetrofitService


/**
 * Created by Wangzhan on 2022/6/1
 *
 * @descr
 */
class DataRepository {
    private var network = RetrofitService.createService(DataApi::class.java)


    suspend fun loadData(pageId: Int): DemoReqData {
        return network.getData(pageId)
    }
}