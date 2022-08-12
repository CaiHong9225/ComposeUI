package com.wtt.composetest.repository.hilt.singleton

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr 三方类注解Module
 */
@InstallIn(SingletonComponent::class)
@Module
class MyAppModule {
    @Provides
    fun providerSingleGlobal(): SingleGlobalData {
        return SingleGlobalData()
    }
}