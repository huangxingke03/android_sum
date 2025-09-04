package com.example.module_home.ui.livedata

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.common.LogUtils
import com.example.common.adapter.ListAdapter
import com.example.common.content.PagePath
import com.example.module_home.R
import com.example.module_home.databinding.ActivityLiveDataFunctionBinding
import com.example.module_home.ui.vm.LiveDataViewModel

@Route(path = PagePath.ModuleMainPage.LIVE_DATA_PAGE)
class LiveDataFunctionActivity : AppCompatActivity() {
    val liveDataFunctionList = arrayListOf(
        "复现数据倒灌",
        "flowOf",
        "asFlow",
        "Livedata只发一次事件修复数据倒灌"
    )
    val liveDataViewModel by lazy {
        ViewModelProvider(this).get(LiveDataViewModel::class)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val liveDataFunctionDataBind =
            DataBindingUtil.setContentView<ActivityLiveDataFunctionBinding>(
                this,
                R.layout.activity_live_data_function
            )
        val listAdapter = ListAdapter().apply {
            submitList(liveDataFunctionList)
        }
        listAdapter.setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener<String> {
            override fun onClick(
                adapter: BaseQuickAdapter<String, *>,
                view: View,
                position: Int
            ) {
                when (position) {
                    0 -> test1()
                    3 -> test2()
                }
            }
        })
        liveDataFunctionDataBind.liveDataFunction.adapter = listAdapter
        liveDataFunctionDataBind.liveDataFunction.layoutManager = LinearLayoutManager(this)
        init()
    }

    fun init() {
        liveDataViewModel.data1.observe(this) { data ->
            LogUtils.d("livedata数据 复现数据倒灌 data1 --->$data")
        }
        liveDataViewModel.data2.observe(this) { data ->
            LogUtils.d("livedata数据 修复数据倒灌只触发一次 data2 --->$data")
        }
    }

    fun test1() {
        liveDataViewModel.setDataValue1("更新数据->复现数据倒灌")
    }

    fun test2() {
        liveDataViewModel.setDataValue2("更新数据->解决数据倒灌(事件只触发一次)")
    }
}