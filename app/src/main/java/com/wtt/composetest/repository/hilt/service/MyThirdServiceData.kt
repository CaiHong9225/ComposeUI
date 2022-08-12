package com.wtt.composetest.repository.hilt.service

import com.wtt.composetest.extension.log


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr 注解到Service的Class  三方无法注解
 */
class MyThirdServiceData {
    private val TAG = "MyThirdServiceData"

    init {
        "init".log(TAG)
    }
}