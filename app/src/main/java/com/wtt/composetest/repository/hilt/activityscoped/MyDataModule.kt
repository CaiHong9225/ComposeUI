package com.wtt.composetest.repository.hilt.activityscoped

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr  三方类注解Module
 */
@InstallIn(ActivityComponent::class)
@Module
class MyDataModule {
    @Provides
    fun providerSimpleGlobal(): ThirdSimpleData {
        return ThirdSimpleData()
    }
}