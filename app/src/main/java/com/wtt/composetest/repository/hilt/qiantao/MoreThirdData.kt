package com.wtt.composetest.repository.hilt.qiantao

import android.content.Context
import com.wtt.composetest.extension.log
import com.wtt.composetest.repository.hilt.activityscoped.SimpleData


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr 嵌套使用注解Class  自有类
 */
class MoreThirdData constructor(
    val simpleData: SimpleData,
    val context: Context
) {
    init {
        "MoreThirdData init".log("MoreThirdData")
    }

}