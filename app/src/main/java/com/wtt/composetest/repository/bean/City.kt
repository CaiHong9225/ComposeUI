package com.wtt.composetest.repository.bean

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
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

//自定义的保存状态
val CitySaver = run {
    //定义键值
    val nameKey = "name"
    val countryKey = "Country"
    mapSaver(
        save = { mapOf(nameKey to it.name, countryKey to it.country) },
        restore = { City(it[nameKey] as String, it[countryKey] as String) }
    )
}

//为了避免需要为映射定义键，您也可以使用 listSaver 并将其索引用作键：
val CitySaverList = run {
    listSaver<City, Any>(
        //利用索引生成键值
        save = { listOf(it.name, it.country) },
        restore = { City(it[0] as String, it[1] as String) }
    )
}

@Composable
fun CityScreen() {
    //保存数据状态
    var selectedCity = rememberSaveable {
        mutableStateOf(City("Mark", "Spain"))
    }
    //使用自定义的保存状态
    var selectedCityMapSaver = rememberSaveable(stateSaver = CitySaver) {
        mutableStateOf(City("Mark", "Spain"))
    }
    //使用自定义的保存状态
    var selectedCityListSaver = rememberSaveable(stateSaver = CitySaverList) {
        mutableStateOf(City("Mark", "Spain"))
    }
}
