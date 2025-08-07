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
        "数据更新",
        "flowOf",
        "asFlow"
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
                }
            }
        })
        liveDataFunctionDataBind.liveDataFunction.adapter = listAdapter
        liveDataFunctionDataBind.liveDataFunction.layoutManager = LinearLayoutManager(this)
        liveDataViewModel.setDataValue("第一次初始化")
        liveDataViewModel.data1.observe(this) { data ->
            LogUtils.d("livedata数据 收到 --->$data")
        }
    }

    fun test1() {
        liveDataViewModel.setDataValue("数据更新")
    }
}