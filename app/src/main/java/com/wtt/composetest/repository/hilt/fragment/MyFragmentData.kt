package com.wtt.composetest.repository.hilt.fragment

import com.wtt.composetest.extension.log
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr 自有class注解到Fragment
 */
@FragmentScoped
class MyFragmentData @Inject constructor() {

    private val TAG = "MyFragmentData"

    init {
        "init".log(TAG)
    }
}