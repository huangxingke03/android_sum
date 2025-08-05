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
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FlowViewModel : ViewModel() {
    val stateFlow = MutableStateFlow(String)
    val shareFlow = MutableSharedFlow<String>()
    var time = 0

    fun getFlow1(): Flow<Int> {
        return flow {
            while (time < 10) {
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

    private fun requestNet(block: (String) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                LogUtils.d("模拟网络")
                delay(1000)
                block.invoke("网络加载结束")
            }
        }

    }
}