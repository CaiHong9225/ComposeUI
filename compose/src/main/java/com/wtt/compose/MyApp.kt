package com.wtt.ourcompose.ui

import android.app.Application
import android.content.Context


/**
 * Created by Wangzhan on 2021/10/12
 *
 * @descr
 */
class MyApp : Application() {
    companion object {
       var instance:MyApp?=null
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}