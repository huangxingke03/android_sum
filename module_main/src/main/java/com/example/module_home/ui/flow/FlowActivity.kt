package com.example.module_home.ui.flow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.LogUtils
import com.example.common.content.PagePath
import com.example.module_home.R
import com.example.module_home.databinding.ActivityFlowBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Route(path = PagePath.ModuleMainPage.FLOW_PAGE)
class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val flowBind =
            DataBindingUtil.setContentView<ActivityFlowBinding>(this, R.layout.activity_flow)
        flowBind.button1.setOnClickListener {
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