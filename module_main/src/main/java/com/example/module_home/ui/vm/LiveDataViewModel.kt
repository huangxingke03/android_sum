package com.example.module_home.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.LogUtils
import com.example.module_home.ui.data.MyLiveData
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class LiveDataViewModel : ViewModel() {
    var data1 = MutableLiveData<String>()
    var data2 = MyLiveData<String>()
    var data3 = MutableLiveData<String>()
    var totalData = MutableLiveData<Int>()
    private val mutex = Mutex()

    fun getDataValue(): String? {
        return data1.value
    }

    fun setDataValue1(value: String) {
        data1.value = value
    }

    fun setDataValue2(value: String) {
        data2.value = value
    }
    fun setDataValue3(value: String) {
        LogUtils.d("setDataValue3 ：  $value ")
        data3.postValue(value)
    }
    fun update() {
        val jobs = mutableListOf<Job>()
        viewModelScope.launch {
            repeat(4) {
                val job = viewModelScope.launch {
//                    mutex.withLock {
//                        LogUtils.d("协程 $it 开始执行")
//                        delay(2000)
//                        LogUtils.d("协程 $it 执行完成")
//                        delay(2000)
//                    }
                    LogUtils.d("协程 $it 开始执行")
                    delay(2000)
                    LogUtils.d("协程 $it 执行完成")
                    delay(2000)
                }
                jobs.add(job)
            }
            jobs.joinAll()
        }


//        viewModelScope.launch(CoroutineName("协成编号$number")) {
//            mutex.withLock {
//                val name = coroutineContext[CoroutineName]?.name
//                delay(3000)
//                LogUtils.d("协成逻辑执行完毕  协成信息  : $name")
//            }
//
//        }
    }

    //
//    fun setDataValue(value: Int) {
//        totalData.value = value
//        LogUtils.d(" totalData : $value")
//    }
    fun setDataValue(value: Int) {
        totalData.value = value
        LogUtils.d(" totalData : $value")
    }
}