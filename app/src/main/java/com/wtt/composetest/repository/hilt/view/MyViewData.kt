package com.wtt.composetest.repository.hilt.view

import com.wtt.composetest.extension.log
import dagger.hilt.android.scopes.ViewScoped
import javax.inject.Inject


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr
 */

@ViewScoped
class MyViewData @Inject constructor() {

    private val TAG = "MyViewData"

    init {
        "init".log(TAG)
    }

}