package com.example.module_home.ui.flow

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.common.LogUtils
import com.example.common.adapter.ListAdapter
import com.example.common.content.PagePath
import com.example.module_home.R
import com.example.module_home.databinding.ActivityFlowFunctionBinding
import com.example.module_home.ui.vm.FlowViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.launch

@Route(path = PagePath.ModuleMainPage.FLOW_PAGE)
class FlowFunctionActivity : AppCompatActivity() {
    val flowFunctionList = arrayListOf(
        "flow",
        "flowOf",
        "asFlow",
        "callBackFlow",
        "channelFlow",
        "collectLatest",
        "flow toList",
        "flow toSet",
        "flow toCollection",
        "stateflow normal",
        "stateflow list",
        "stateflow",
        "shared Flow1",
        "shared Flow2",
        "shared Flow 连续注册多个",
        "shared Flow",
    )
    var dataList = mutableListOf<String>()
    var dataSet = mutableSetOf<String>()
    var dataCollection = mutableListOf<String>()
    val flowModel by lazy {
        ViewModelProvider(this).get(FlowViewModel::class.java)
    }
    var flowFunctionDataBind: ActivityFlowFunctionBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flowFunctionDataBind = DataBindingUtil.setContentView<ActivityFlowFunctionBinding>(
            this, R.layout.activity_flow_function
        )
        val listAdapter = ListAdapter().apply {
            submitList(flowFunctionList)
        }
        listAdapter.setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener<String> {
            override fun onClick(
                adapter: BaseQuickAdapter<String, *>,
                view: View,
                position: Int
            ) {
                when (position) {
                    0 -> flow1()
                    1 -> flow2()
                    2 -> flow3()
                    3 -> flow4()
                    4 -> flow5()
                    5 -> flow6()
                    6 -> flow7()
                    7 -> flow8()
                    8 -> flow9()
                    9 -> stateflowNormal()
                    10 -> flow11()
                    11 -> stateflow()
                    12 -> flow13()
                    13 -> flow14()
                    14 -> flow15()
                }
            }
        })
        flowFunctionDataBind?.flowFunction?.adapter = listAdapter
        flowFunctionDataBind?.flowFunction?.layoutManager = LinearLayoutManager(this)
        init()
    }

    fun init() {
        lifecycleScope.launch {
            flowModel.stateFlow1.collect { value ->
                LogUtils.d("stateflow collect  : $value")
                flowFunctionDataBind?.flowText1?.text = value
            }
        }
    }

    fun flow1() {
        lifecycleScope.launch {
            flowModel.getFlow1().collect { value ->
                LogUtils.d("flow  collect :$value")
            }
        }
    }

    fun flow2() {
        lifecycleScope.launch {
//                flowModel.getFlow2().collect { value ->
//                    LogUtils.d("flowOf  collect :$value")
//                }
            flowModel.getFlow2().collectIndexed { index, value ->
                LogUtils.d("flowOf  collectIndexed   index= :$index ,value= :$value")
            }
        }
    }

    fun flow3() {
        lifecycleScope.launch {
            flowModel.getFlow3().collect { value ->
                LogUtils.d("asflow  collect :$value")
            }
        }
    }

    fun flow4() {
        lifecycleScope.launch {
            flowModel.getFlow4().collect { value ->
                LogUtils.d("callBackflow  collect :$value")
            }
        }
    }

    fun flow5() {
        lifecycleScope.launch {
            flowModel.getFlow5().collect { value ->
                val collectCurrentThreadname = Thread.currentThread().name
                LogUtils.d("channelFlow   collectCurrentThreadname : $collectCurrentThreadname    collect : $value")
            }
        }
    }

    fun flow6() {
        lifecycleScope.launch {
            flowModel.getFlow6().collectLatest { value ->
                delay(500)
                LogUtils.d("collectLatest   value : $value")
            }
        }
    }

    fun flow7() {
        lifecycleScope.launch {
            LogUtils.d("flow toList")
            dataList.addAll(flowModel.getFlow7().toList())
            LogUtils.d(dataList)
        }
    }

    fun flow8() {
        lifecycleScope.launch {
            LogUtils.d("flow toSet")
            dataSet.addAll(flowModel.getFlow8().toSet())
            LogUtils.d(dataSet)
        }
    }

    fun flow9() {
        lifecycleScope.launch {
            LogUtils.d("flow toCollection")
            flowModel.getFlow9().toCollection(dataCollection)
            LogUtils.d(dataCollection)
        }
    }

    fun stateflowNormal() {
//        flowModel.getStateFlow1()

    }

    fun flow11() {
        flowModel.getStateFlow2()
        lifecycleScope.launch {
            flowModel.stateFlow2.collect { list ->
                LogUtils.d("stateflowlist 111 flow11 collect  : $list")
            }
        }
        lifecycleScope.launch {
            flowModel.stateFlow2.collect { list ->
                LogUtils.d("stateflowlist 222 flow11 collect  : $list")
            }
        }
    }

    fun stateflow() {
//        lifecycleScope.launch {
//            flowModel.stateFlow2.collect { list ->
//                LogUtils.d("stateflowlist flow12 collect  : $list")
//            }
//        }
        flowModel.getStateFlow1()
    }

    fun flow13() {
        flowModel.updateShareFlow1()
        lifecycleScope.launch {
            flowModel._shareFlow.collect { value ->
                LogUtils.d("shareflow  collect1  : $value")
            }
        }
    }

    fun flow14() {
        flowModel.updateShareFlow2()
        lifecycleScope.launch {
            flowModel._shareFlow.collect { value ->
                LogUtils.d("shareflow  collect2  : $value")
            }
        }
    }

    fun flow15() {
        flowModel.updateShareFlow1()
        lifecycleScope.launch {
            delay(200)
            flowModel.shareFlow.collect { value ->
                LogUtils.d("flow15  collect1  : $value")
            }
        }
        lifecycleScope.launch {
            delay(100)
            flowModel.shareFlow.collect { value ->
                LogUtils.d("flow15  collect2  : $value")
            }
        }
    }
}