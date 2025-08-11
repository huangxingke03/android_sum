package com.example.module_home.ui.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ComposeViewModel : ViewModel() {
    private var _titleFlow = MutableStateFlow<String>("compose默认标题")
    val titleFlow = _titleFlow.asStateFlow()

    fun updateTitle(newTitleValue: String) {
        _titleFlow.value = newTitleValue
    }
}