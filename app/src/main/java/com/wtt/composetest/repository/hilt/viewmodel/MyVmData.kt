package com.wtt.composetest.repository.hilt.viewmodel

import android.util.Log
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr
 */
@ViewModelScoped
class MyVmData @Inject  constructor() {
    private val TAG = "MyVmData"

    init {
        Log.d(TAG, "int")
    }


}