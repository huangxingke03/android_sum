package com.example.module_home.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.LogUtils
import com.example.common.content.PagePath
import com.example.module_home.R
import com.example.module_home.databinding.ActivityFlowOrLivedataBinding
import com.example.module_home.ui.vm.FlowViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Route(path = PagePath.ModuleMainPage.FLOW_PAGE)
class FlowOrLiveDataActivity : AppCompatActivity() {
    val flowModel by lazy {
        ViewModelProvider(this).get(FlowViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val flowOrLiveDataBind =
            DataBindingUtil.setContentView<ActivityFlowOrLivedataBinding>(this, R.layout.activity_flow_or_livedata)


        flowOrLiveDataBind.button1.setOnClickListener {
            LogUtils.d("FlowActivity  button1")
        }
    }

    fun simple(): Flow<Int> = flow { // 流构建器
        for (i in 1..3) {
            delay(100) // 假装我们在这里做了一些有用的事情
            emit(i) // 发送下一个值
        }
    }
}