package com.wtt.composetest.repository.hilt.qiantao

import android.content.Context
import com.wtt.composetest.extension.log
import com.wtt.composetest.repository.hilt.activityscoped.SimpleData
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr 嵌套使用注解Class  自有类  嵌套不用定义Scope  一种直接注解
 */
class MoreData @Inject constructor(
    val simpleData: SimpleData,
    @ActivityContext val context: Context
) {
    init {
        "MoreData init".log("MoreData")
    }
}