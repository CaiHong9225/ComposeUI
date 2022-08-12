package com.wtt.composetest.repository.bean

import android.os.Parcelable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.flow.MutableSharedFlow


/**
 * Created by Wangzhan on 2021/8/24
 *
 * @descr 打包状态
 */
@Parcelize
data class City(val name: String, val country: String) : Parcelable



