package com.example.module_home.ui.lock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.content.PagePath
import com.example.module_home.R
import com.example.module_home.databinding.ActivityLockBinding
import com.example.module_home.ui.vm.LiveDataViewModel

@Route(path = PagePath.ModuleMainPage.LOCK_PAGE)
class LockActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLockBinding
    val liveDataViewModel by lazy {
        ViewModelProvider(this).get(LiveDataViewModel::class)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityLockBinding>(this, R.layout.activity_lock)
        binding.lockTest1.setOnClickListener {
            liveDataViewModel.update()
        }
        binding.lockTest2.setOnClickListener {

        }
        binding.lockTest3.setOnClickListener {
            liveDataViewModel.update()
        }
    }
}