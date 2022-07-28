package com.wtt.composetest.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wtt.composetest.repository.UserRepository
import com.wtt.composetest.repository.bean.Result
import com.wtt.composetest.repository.entity.UserInfo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Created by Wangzhan on 2022/5/6
 *
 * @descr
 */
class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    companion object {
        val TAG: String = this.javaClass.simpleName
    }
    private var _user = MutableLiveData<Result<UserInfo>>()
    val user = _user
    fun loadUser(userId: String) {
        var coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d(TAG, "get:" + throwable.message)
        }
        viewModelScope.launch(SupervisorJob() + coroutineExceptionHandler) {
            val respose = userRepository.loadUserInfo(userId = userId)
            if (respose.isSuccess()) {
                user.value = Result.Success(respose.data)
            } else {
                user.value = Result.Error(Exception(respose.message))
            }

        }
    }
}