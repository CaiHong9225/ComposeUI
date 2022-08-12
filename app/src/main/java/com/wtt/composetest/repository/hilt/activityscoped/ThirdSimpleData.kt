package com.wtt.composetest.repository.hilt.activityscoped

import android.util.Log


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr 模仿三方库不能加Inject注解的类
 */
class ThirdSimpleData {
    private val TAG = "ThirdSimpleData"

    init {
        Log.d(TAG, "int")
    }

    fun deal() {
        Log.d(TAG, "deal")
    }
}