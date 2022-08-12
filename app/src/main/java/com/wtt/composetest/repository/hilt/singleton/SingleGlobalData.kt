package com.wtt.composetest.repository.hilt.singleton

import android.util.Log


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr 三方Application引用
 */
class SingleGlobalData {
    private val TAG = "SingleGlobalData"

    init {
        Log.d(TAG, "int")
    }

}