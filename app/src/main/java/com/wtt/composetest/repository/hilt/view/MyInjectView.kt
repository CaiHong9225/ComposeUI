package com.wtt.composetest.repository.hilt.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr
 */
@AndroidEntryPoint
class MyInjectView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    @Inject
    lateinit var data: MyThirdViewData

    @Inject
    lateinit var data2:MyViewData
}