package com.wtt.composetest.extension

import android.util.Log


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr
 */

fun String.log(tag: String) {
    Log.i(tag, "log: $this")
}