package com.wtt.composetest.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Created by Wangzhan on 2021/8/26
 *
 * @descr
 */
class LoginViewModel(private val loginText: String) : ViewModel() {

//    fun login(userName:String,passWord:String) {
//        viewModelScope.launch(Dispatchers.Default) {
//            if (userName.get().isNullOrBlank() || passWord.get().isNullOrBlank()) return@launch
//
//            withContext(Dispatchers.Main) { showLoading() }
//
//            val result = repository.login(userName.get() ?: "", passWord.get() ?: "")
//
//            withContext(Dispatchers.Main) {
//                if (result is Result.Success) {
//                    emitUiState(showSuccess = result.data, enableLoginButton = true)
//                } else if (result is Result.Error) {
//                    emitUiState(showError = result.exception.message, enableLoginButton = true)
//                }
//            }
//        }
//    }

}