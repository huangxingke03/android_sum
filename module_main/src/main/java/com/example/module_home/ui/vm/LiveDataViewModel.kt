package com.example.module_home.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel : ViewModel() {
    var data1 = MutableLiveData<String>()
    fun getDataValue():String? {
       return data1.value
    }

    fun setDataValue(value: String) {
        data1.value = value
    }
}