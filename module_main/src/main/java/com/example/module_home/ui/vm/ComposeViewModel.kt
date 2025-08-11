package com.example.module_home.ui.vm

import androidx.lifecycle.ViewModel
import com.example.module_home.ui.data.BodyItemInfo
import com.example.module_utils.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ComposeViewModel : ViewModel() {
    private var _titleFlow = MutableStateFlow<String>("compose默认标题")
    val titleFlow = _titleFlow.asStateFlow()

    private var _bodyListFlow = MutableStateFlow<List<BodyItemInfo>>(emptyList())
    val bodyListFlow = _bodyListFlow.asStateFlow()

    init {
        initBodyList()
    }

    fun updateTitle(newTitleValue: String) {
        _titleFlow.value = newTitleValue
    }

    fun initBodyList() {
        val bodyDataList = listOf(
            R.drawable.ab1_inversions to R.string.ab1_inversions,
            R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
            R.drawable.ab3_stretching to R.string.ab3_stretching,
            R.drawable.ab4_tabata to R.string.ab4_tabata,
            R.drawable.ab5_hiit to R.string.ab5_hiit,
            R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
        ).map {
            BodyItemInfo().apply {
                drawableRes = it.first
                titleRes = it.second
            }
        }
        _bodyListFlow.value = bodyDataList
    }
}