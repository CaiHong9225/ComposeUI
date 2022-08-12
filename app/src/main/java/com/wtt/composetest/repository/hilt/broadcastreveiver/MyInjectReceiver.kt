package com.wtt.composetest.repository.hilt.broadcastreveiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.wtt.composetest.extension.log
import com.wtt.composetest.repository.hilt.singleton.SingleData
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr 通过EntryPointAccessors这个类的方法，它有4种获取的方法，对应4种作用域，
 * 分别为fromApplication，fromActivity，fromFragment，fromView，
 * 获取的类型就是下面定义的接口MySingleTom，然后通过里面的方法，就可以获取到你想要的对象了
 */
class MyInjectReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if ("com.caesar.hit.normal.broad" == intent?.action) {
            val singletom = EntryPointAccessors.fromApplication<MyReceiverData>(context!!)//SingletonComponent -> fromApplication
            ("收到了和广播:" + singletom.getData()).log("MyInjectReceiver")
        }
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface MyReceiverData {
        fun getData(): SingleData
    }
}