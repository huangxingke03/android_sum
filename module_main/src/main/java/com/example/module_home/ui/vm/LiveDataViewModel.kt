package com.example.module_home.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.module_home.ui.data.MyLiveData

class LiveDataViewModel : ViewModel() {
    var data1 = MutableLiveData<String>()
    var data2 = MyLiveData<String>()
    fun getDataValue(): String? {
        return data1.value
    }

    fun setDataValue1(value: String) {
        data1.value = value
    }
    fun setDataValue2(value: String) {
        data2.value = value
    }
}