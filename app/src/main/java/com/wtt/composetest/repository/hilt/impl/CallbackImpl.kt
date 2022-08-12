package com.wtt.composetest.repository.hilt.impl

import android.content.Context
import com.wtt.composetest.extension.log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr
 */
class CallbackImpl @Inject constructor(@ApplicationContext context: Context) :
    ICallback {
    override fun onData() {
        "onData".log("CallbackImpl")
    }

    override fun onDes() {
        "onDes".log("CallbackImpl")
    }
}