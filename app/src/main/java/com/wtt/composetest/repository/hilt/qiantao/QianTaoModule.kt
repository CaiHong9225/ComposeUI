package com.wtt.composetest.repository.hilt.qiantao

import android.content.Context
import com.wtt.composetest.repository.hilt.activityscoped.SimpleData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr  两种形式 一种直接注解 一种是Provider
 */
@InstallIn(ActivityComponent::class)
@Module
class QianTaoModule {
    @Provides
    fun providerMoreGlobal(
        singleData: SimpleData,
        @ApplicationContext context: Context
    ): MoreThirdData {
        return MoreThirdData(singleData, context)
    }
}