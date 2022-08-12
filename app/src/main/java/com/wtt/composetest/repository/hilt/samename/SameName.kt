package com.wtt.composetest.repository.hilt.samename

import com.wtt.composetest.extension.log


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr
 */

class SameName constructor(val name: String) {
    init {
        "init".log("SameName")
    }

    override fun toString(): String {
        return "SameName(name='$name')"
    }

}