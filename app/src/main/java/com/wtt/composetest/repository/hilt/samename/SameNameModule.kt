package com.wtt.composetest.repository.hilt.samename

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr  不同名字 用Name区分
 */
@InstallIn(ActivityComponent::class)
@Module
class SameNameModule {

    @Named("John")
    @Provides
    fun providerName1():SameName{
        return SameName("John")
    }
    @Named("Kotlin")
    @Provides
    fun providerName2():SameName{
        return SameName("Kotlin")
    }
}