package com.example.module_home.ui.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class FlowViewModel : ViewModel() {
    val stateFlow = MutableStateFlow(String)
    val shareFlow = MutableSharedFlow<String>()
    var time = 0
    fun getFlow1(): Flow<Int> {
        return flow {
            while (time < 10) {
                time++
                emit(1)
            }
        }
    }
}