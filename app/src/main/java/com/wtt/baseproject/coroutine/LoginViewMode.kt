package com.wtt.mykotlin.coroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wtt.mykotlin.repository.LoginRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


/**
 * Created by Wangzhan on 2022/2/11
 *
 * @descr
 */
class LoginViewMode(private val loginRepository: LoginRepository) : ViewModel() {


    fun login(userName: String, token: String) {
        viewModelScope.launch {

            val jsonBody = "{ username: \"$userName\", token: \"$token\"}"
            //切IO
            val result = loginRepository.makeLoginRequest(jsonBody)

            //搞到结果 谁知liveData
            viewModelScope.cancel()
        }
    }
}