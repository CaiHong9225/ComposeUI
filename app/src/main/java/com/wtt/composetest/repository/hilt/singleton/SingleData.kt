package com.wtt.composetest.repository.hilt.singleton

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr
 */
@Singleton
class SingleData @Inject constructor() {

    private val TAG = "SingleData"

    init {
        Log.d(TAG, "int")
    }

}