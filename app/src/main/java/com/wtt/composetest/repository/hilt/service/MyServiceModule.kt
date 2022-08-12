package com.wtt.composetest.repository.hilt.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr 三方服务调用的Module
 */
@InstallIn(ServiceComponent::class)
@Module
class MyServiceModule {
    @Provides
    fun providerSerFrag(): MyThirdServiceData {
        return MyThirdServiceData()
    }
}