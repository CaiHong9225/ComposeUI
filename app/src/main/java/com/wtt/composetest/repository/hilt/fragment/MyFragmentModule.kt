package com.wtt.composetest.repository.hilt.fragment

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr 三方调用Class 注解Module
 */
@InstallIn(FragmentComponent::class)
@Module
class MyFragmentModule {
    @Provides
    fun providerMyFragmentData(): MyThirdFragmentData {
        return MyThirdFragmentData()
    }
}