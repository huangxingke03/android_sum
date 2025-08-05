package com.example.module_home.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.LogUtils
import com.example.common.content.PagePath
import com.example.module_home.R
import com.example.module_home.databinding.ActivityFlowOrLivedataBinding
import com.example.module_home.ui.vm.FlowViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.launch

@Route(path = PagePath.ModuleMainPage.FLOW_PAGE)
class FlowOrLiveDataActivity : AppCompatActivity() {
    var dataList = mutableListOf<String>()
    var dataSet = mutableSetOf<String>()
    var dataCollection= mutableListOf<String>()
    val flowModel by lazy {
        ViewModelProvider(this).get(FlowViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val flowOrLiveDataBind =
            DataBindingUtil.setContentView<ActivityFlowOrLivedataBinding>(
                this,
                R.layout.activity_flow_or_livedata
            )
        flowOrLiveDataBind.flow1.setOnClickListener {
            lifecycleScope.launch {
                flowModel.getFlow1().collect { value ->
                    LogUtils.d("flow  collect :$value")
                }
            }
        }
        flowOrLiveDataBind.flow2.setOnClickListener {
            lifecycleScope.launch {
//                flowModel.getFlow2().collect { value ->
//                    LogUtils.d("flowOf  collect :$value")
//                }
                flowModel.getFlow2().collectIndexed { index, value ->
                    LogUtils.d("flowOf  collectIndexed   index= :$index ,value= :$value")
                }
            }
        }
        flowOrLiveDataBind.flow3.setOnClickListener {
            lifecycleScope.launch {
                flowModel.getFlow3().collect { value ->
                    LogUtils.d("asflow  collect :$value")
                }
            }
        }
        flowOrLiveDataBind.flow4.setOnClickListener {
            lifecycleScope.launch {
                flowModel.getFlow4().collect { value ->
                    LogUtils.d("callBackflow  collect :$value")
                }
            }
        }
        flowOrLiveDataBind.flow5.setOnClickListener {
            lifecycleScope.launch {
                flowModel.getFlow5().collect { value ->
                    val collectCurrentThreadname = Thread.currentThread().name
                    LogUtils.d("channelFlow   collectCurrentThreadname : $collectCurrentThreadname    collect : $value")
                }
            }
        }
        flowOrLiveDataBind.flow6.setOnClickListener {
            lifecycleScope.launch {
                flowModel.getFlow6().collectLatest { value ->
                    delay(500)
                    LogUtils.d("collectLatest   value : $value")
                }
            }
        }
        flowOrLiveDataBind.flow7.setOnClickListener {
            lifecycleScope.launch {
                LogUtils.d("flow toList")
                dataList.addAll(flowModel.getFlow7().toList())
                LogUtils.d(dataList)
            }
        }
        flowOrLiveDataBind.flow8.setOnClickListener {
            lifecycleScope.launch {
                LogUtils.d("flow toSet")
                dataSet.addAll(flowModel.getFlow8().toSet())
                LogUtils.d(dataSet)
            }
        }
        flowOrLiveDataBind.flow9.setOnClickListener {
            lifecycleScope.launch {
                LogUtils.d("flow toCollection")
                flowModel.getFlow9().toCollection(dataCollection)
                LogUtils.d(dataCollection)
            }
        }
    }
}