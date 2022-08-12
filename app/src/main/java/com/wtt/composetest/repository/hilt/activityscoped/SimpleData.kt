package com.wtt.composetest.repository.hilt.activityscoped

import android.util.Log
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr 自有类可以直接注解
 */
@ActivityScoped //不写就是SingleTon的Scope
class SimpleData @Inject constructor() {
    private val TAG = "SimpleData"

    init {
        Log.d(TAG, "int")
    }

    fun deal() {
        Log.d(TAG, "deal")
    }
}