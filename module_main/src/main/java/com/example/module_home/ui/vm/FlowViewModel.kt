package com.example.module_home.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FlowViewModel : ViewModel() {
    val _stateFlow1 = MutableStateFlow("stateflow数据没更新")
    var stateFlow1 = _stateFlow1.asStateFlow()

    val _stateFlow2 = MutableStateFlow<List<String>>(emptyList())
    var stateFlow2 = _stateFlow2.asStateFlow()

    val _shareFlow = MutableSharedFlow<String>(2, 6)
    var shareFlow=_shareFlow.asSharedFlow()
    var time = 0
    var maxIndex = 5

    fun getFlow1(): Flow<Int> {
        return flow {
            while (time < 5) {
                time++
                emit(time)
            }
        }
    }

    fun getFlow2(): Flow<String> {
        return flowOf("flowOf1", "flowOf2", "flowOf3")
    }

    fun getFlow3(): Flow<String> {
        return listOf("listFlowItem1", "listFlowItem2", "listFlowItem3").asFlow()
    }

    fun getFlow4(): Flow<String> {
        return callbackFlow {
            requestNet { requestState ->
                trySend(requestState)
            }
            awaitClose {
                LogUtils.d("网络操作取消重置")
            }
        }
    }

    fun getFlow5(): Flow<String> {
        return channelFlow {
            val currentThreadName = Thread.currentThread().name
            send("当前线程信息 :$currentThreadName")
            withContext(Dispatchers.IO) {
                val currentIoThreadName = Thread.currentThread().name
                send("切换线程后当前线程信息 : $currentIoThreadName")
            }
        }
    }

    fun getFlow6(): Flow<Int> {
        return flow {
            while (time < 5) {
                time++
                emit(time)
                LogUtils.d("---getFlow6---emit--- :$time")
            }
        }
    }

    fun getFlow7(): Flow<String> {
        return flow {
            while (maxIndex >= 0) {
                emit("list条目 <--> index:$maxIndex")
                maxIndex--
            }
        }
    }

    fun getFlow8(): Flow<String> {
        return flow {
            while (maxIndex >= 0) {
                emit("set条目 <--> index:$maxIndex")
                maxIndex--
            }
        }
    }

    fun getFlow9(): Flow<String> {
        return flow {
            while (maxIndex >= 0) {
                emit("collection条目 <--> index:$maxIndex")
                maxIndex--
            }
        }
    }

    fun getStateFlow1() {
        _stateFlow1.value = "stateFlow数据更新"
//        viewModelScope.launch {
//            delay(800)
//            _stateFlow1.value = "stateFlow执行耗时操作,最终执行成功"
//        }
    }

    fun getStateFlow2() {
        viewModelScope.launch {
            delay(800)
            _stateFlow2.value = arrayListOf("StateFlowItem1", "StateFlowItem2", "StateFlowItem3")
        }
    }

    fun updateShareFlow1() {
        time = 0
        viewModelScope.launch {
            while (time < 6) {
                time++
                delay(50)
                _shareFlow.emit("shareFlow update1 index : $time")
            }
        }
    }

    fun updateShareFlow2() {
        time = 0
        while (time < 6) {
            time++
            _shareFlow.tryEmit("shareFlow update2 index : $time")
        }
    }

    private fun requestNet(block: (String) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                LogUtils.d("模拟连接网络")
                delay(1000)
                block.invoke("网络加载结束")
            }
        }

    }
}