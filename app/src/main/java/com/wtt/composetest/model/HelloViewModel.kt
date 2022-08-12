package com.wtt.composetest.model


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * Created by Wangzhan on 2021/8/24
 *
 * @descr
 */
class HelloViewModel : ViewModel() {

    // LiveData holds state which is observed by the UI
    // (state flows down from ViewModel)
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    // onNameChange is an event we're defining that the UI can invoke
    // (events flow up from UI)
    fun onNameChange(newName: String) {
        _name.value = newName
    }
}


