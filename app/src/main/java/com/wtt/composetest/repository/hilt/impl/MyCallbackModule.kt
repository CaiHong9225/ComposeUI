package com.wtt.composetest.repository.hilt.impl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named


/**
 * Created by Wangzhan on 2022/8/3
 *
 * @descr 接口类的注入也有点不同，因为是抽象的，所以在绑定的时候，类也要是抽象类。然后这边不再是provider标签了，要用Binds标签。
 */
@InstallIn(ActivityComponent::class)
@Module
abstract class MyCallbackModule {

    @Binds
    abstract fun providerCallback(callbackImpl: CallbackImpl): ICallback

    @Named("bind")
    @Binds
    abstract fun bindCallback(callbackImpl: CallbackImpl): ICallback

}