package com.wtt.composetest.repository.hilt

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.wtt.composetest.R
import com.wtt.composetest.repository.hilt.activityscoped.SimpleData
import com.wtt.composetest.repository.hilt.activityscoped.ThirdSimpleData
import com.wtt.composetest.repository.hilt.impl.ICallback
import com.wtt.composetest.repository.hilt.qiantao.MoreData
import com.wtt.composetest.repository.hilt.qiantao.MoreThirdData
import com.wtt.composetest.repository.hilt.samename.SameName
import com.wtt.composetest.repository.hilt.singleton.SingleGlobalData
import com.wtt.composetest.repository.hilt.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class SimpleInjectActivity : AppCompatActivity() {
    private val TAG = "SimpleInjectActivity"

    @Inject
    lateinit var simpleData1: SimpleData

    @Inject
    lateinit var simpleData2: SimpleData

    @Inject
    lateinit var simpleThirdData1: ThirdSimpleData

    @Inject
    lateinit var simpleThirdData2: ThirdSimpleData

    @Inject
    lateinit var singleGlobalData: SingleGlobalData //全局注解

    @Inject
    lateinit var moreData: MoreData //嵌套注解

    @Inject
    lateinit var moreThirdData: MoreThirdData

    //重名对象注解
    @Inject
    @Named("Kotlin")
    lateinit var name1: SameName

    @Inject
    @Named("John")
    lateinit var name2: SameName
    //Imp

    @Inject
    lateinit var callback: ICallback

    @Named("bind")
    @Inject
    lateinit var callback2: ICallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_inject)
        simpleData1.deal()
        Log.i(TAG, "simpleData1的地址:$simpleData1")
        Log.i(TAG, "simpleData2的地址:$simpleData2")
        Log.i(TAG, "simpleData:${simpleData2==simpleData1}")
        simpleThirdData1.deal()
        Log.i(TAG, "simpleGlobalData1的地址:$simpleThirdData1")
        Log.i(TAG, "simpleGlobalData2的地址:$simpleThirdData2")
        Log.i(TAG, "simpleGlobalData:${simpleThirdData1==simpleThirdData2}")
        //Note : @Inject不能用于ViewModel 会失去生命周期
        val viewModel by viewModels<MyViewModel>() //ViewModel的注解获取方式


        Log.i(TAG, "name1:$name1")
        Log.i(TAG, "name2:$name2")
        viewModel.check()

        callback.onData()
        callback.onDes()
        //不为同一个对象
        Log.i(TAG, "callback1:$callback")
        Log.i(TAG, "callback2:$callback2")

    }
}