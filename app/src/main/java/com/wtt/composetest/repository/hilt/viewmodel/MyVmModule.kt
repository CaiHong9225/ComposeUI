package com.wtt.composetest.repository.hilt.viewmodel

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr ViewModel调用三方Class
 */
@InstallIn(ViewModelComponent::class)
@Module
class MyVmModule {

    @Provides
    fun providerMyThirdVmModule(): MyThirdVmData {
        //do something get Information
        return MyThirdVmData(1)
    }

}