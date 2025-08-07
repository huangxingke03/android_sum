package com.example.module_home.ui.databind

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.bean.DataBindInfo
import com.example.common.bean.DataBindUser
import com.example.common.content.PagePath
import com.example.module_home.R
import com.example.module_home.databinding.ActivityDataBindTestBinding

@Route(path = PagePath.ModuleMainPage.BIND_TEST_PAGE)
class DataBindTestActivity : AppCompatActivity() {
    lateinit var dataBindTestBind: ActivityDataBindTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBindTestBind = DataBindingUtil.setContentView<ActivityDataBindTestBinding>(
            this,
            R.layout.activity_data_bind_test
        )
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
        //类名一样,路径不一样
        dataBindTestBind.user4 = com.example.common.model.DataBindUser().apply {
            userName = "我是用户4"
            userAddress = ",用户4家住上海徐家汇"
        }
    }
}