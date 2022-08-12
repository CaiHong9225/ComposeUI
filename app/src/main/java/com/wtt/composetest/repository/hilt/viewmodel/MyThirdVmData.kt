package com.wtt.composetest.repository.hilt.viewmodel

import android.util.Log


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr
 */
class MyThirdVmData constructor(var age:Int) {

    private val TAG = "MyThirdVmData"

    init {
        Log.d(TAG, "int")
    }

    override fun toString(): String {
        return "MyThirdVmData(age=$age)"
    }

}