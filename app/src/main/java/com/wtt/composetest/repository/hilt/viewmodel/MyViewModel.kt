package com.wtt.composetest.repository.hilt.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr ViewModel注解 Note add Inject
 */
@HiltViewModel
class MyViewModel @Inject constructor() : ViewModel() {
    private val TAG = "MyViewModel"

    @Inject
    lateinit var vm: MyVmData

    @Inject
    lateinit var vmg: MyThirdVmData

    fun check() {
        Log.i(TAG, "VMData地址:" + vm)
        Log.i(TAG, "VMGlobalData:" + vmg)
    }
}