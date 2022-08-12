package com.wtt.composetest.repository.hilt.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyInjectService : Service() {

    @Inject
    lateinit var data: MyThirdServiceData

    @Inject
    lateinit var data2: MyServiceData
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}