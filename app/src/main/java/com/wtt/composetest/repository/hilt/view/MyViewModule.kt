package com.wtt.composetest.repository.hilt.view

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewComponent


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr
 */
@InstallIn(ViewComponent::class)
@Module
class MyViewModule {

    @Provides
    fun providerMyViewData(): MyThirdViewData {
        return MyThirdViewData()
    }
}