package com.wtt.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wtt.compose.ui.paging.DataSource


/**
 * Created by Wangzhan on 2022/6/1
 *
 * @descr
 */
class MainActivityViewModel : ViewModel() {
    /**
     * 获取数据
     */
    fun getData() = Pager(PagingConfig(pageSize = 8)) {
        DataSource()
    }.flow
}