package com.wtt.mykotlin.delegate


/**
 * Created by Wangzhan on 2022/1/12
 *
 * @descr
 */
class RealSubject(delegate: SubjectDelegate) :Subject by delegate{

    fun test(){
        //这个是Kotlin委托文件
        body()
    }
}