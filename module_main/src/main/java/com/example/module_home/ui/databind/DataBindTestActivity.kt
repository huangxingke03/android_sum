package com.example.module_home.ui.databind

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.adapter.DatabindListAdapter
import com.example.common.bean.DataBindInfo
import com.example.common.bean.DataBindUser
import com.example.common.content.PagePath
import com.example.common.model.BindListData
import com.example.module_home.R
import com.example.module_home.databinding.ActivityDataBindTestBinding
import com.example.module_home.ui.vm.LiveDataViewModel

@Route(path = PagePath.ModuleMainPage.BIND_TEST_PAGE)
class DataBindTestActivity : AppCompatActivity() {
    lateinit var dataBindTestBind: ActivityDataBindTestBinding
    val liveDataViewModel by lazy {
        ViewModelProvider(this)[LiveDataViewModel::class]
    }
    var dataList = ArrayList<BindListData>()
    var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBindTestBind = DataBindingUtil.setContentView<ActivityDataBindTestBinding>(
            this,
            R.layout.activity_data_bind_test
        )
        init()
        dataBindTestBind.bindInfo = DataBindInfo().apply {
            bindInfoName = "绑定数据名字"
            bindInfoNumber = 100

        }
        //类名一样,路径一样,多处绑定
        dataBindTestBind.user2 = DataBindUser().apply {
            userName = "我是用户2"
        }
        dataBindTestBind.user3 = DataBindUser().apply {
            userName = "我是用户3"
        }
        //类名一样,路径不一样绑定
        dataBindTestBind.user4 = com.example.common.model.DataBindUser().apply {
            userName = "我是用户4"
            userAddress = ",用户4家住上海徐家汇"
        }
        //recycleview绑定
        val databindListAdapter = DatabindListAdapter(dataList)
        dataBindTestBind.dataBindList.adapter = databindListAdapter
        dataBindTestBind.dataBindList.layoutManager = LinearLayoutManager(this)
    }

    fun init() {
        for (index in 1..5){
            dataList.add(BindListData().apply {
                itemIndex = "编号 : $index"
                title = "绑定标题 : $index"
                content = "绑定内容 : $index"
            })
        }
    }
}