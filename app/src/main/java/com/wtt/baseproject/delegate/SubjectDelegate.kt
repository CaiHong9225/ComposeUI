package com.wtt.mykotlin.delegate


/**
 * Created by Wangzhan on 2022/1/12
 *
 * @descr
 */
class SubjectDelegate : Subject {
    override fun body() {
        print(
            "IIIIIIIIIIIIIIIIIIIIIIIIIII"
        )
    }

    override val type: String
        get() = "委托类该属性"
}