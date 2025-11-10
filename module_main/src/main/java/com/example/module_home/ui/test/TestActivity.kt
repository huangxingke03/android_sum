package com.example.module_home.ui.test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
import com.example.common.adapter.ListAdapter
import com.example.common.content.PagePath
import com.example.module_home.R
import com.example.module_home.databinding.ActivityTestBinding

@Route(path = PagePath.ModuleMainPage.TEST_PAGE)
class TestActivity : AppCompatActivity() {
    private lateinit var bind: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView<ActivityTestBinding>(this, R.layout.activity_test)
        val listAdapter = ListAdapter().apply {
            submitList(arrayListOf("单例"))
        }
        listAdapter.setOnItemClickListener(object : OnItemClickListener<String> {
            override fun onClick(
                adapter: BaseQuickAdapter<String, *>, view: View, position: Int
            ) {
                when (position) {
                    0 -> test1()
                }
            }
        })
        bind.testFunction.adapter = listAdapter
        bind.testFunction.layoutManager = LinearLayoutManager(this)
    }

    fun test1() {

    }
}