package com.example.module_home.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.ArraySet
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.example.common.HandlerUtils
import com.example.common.JsonUtils
import com.example.common.LogUtils
import com.example.common.StorageUtils1
import com.example.common.content.PagePath
import com.example.common.ui.base.ARouterActivity
import com.example.module_home.R
import com.example.module_home.databinding.ActivityHomeBinding
import com.example.module_home.ui.view.MyView
import com.example.module_home.ui.view.MyViewGroup
import com.example.module_home.ui.vm.TestViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.LinkedBlockingDeque

/**
 * 首页
 */
@Route(path = PagePath.ModuleMainPage.HOME_PAGE)
class HomeActivity : ARouterActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    var arrySet = ArraySet<String>()

    var hashSet = HashSet<String>()
    var linkHashSet = LinkedHashSet<String>()

    var arryList = ArrayList<String>()
    var linkList = LinkedList<String>()

    var hashMap = HashMap<String, String>()
    var hashtable = Hashtable<String, String>()
    var linkHashMap = LinkedHashMap<String, String>()

    var linkHashMapAccessOrderFalse = LinkedHashMap<String, String>(16, 0.75f, true)
    var time = 10
    var url1 = "http://pic1.win4000.com/wallpaper/c/58f8211a3a604.jpg"
    val model by lazy {
        ViewModelProvider(this).get(TestViewModel::class.java)
    }

    var handle1 = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0 -> {
                    LogUtils.d("------handleMessage---收到处理同步消息----")
                }

                1 -> {

                }

                else -> {

                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var homeBind =
            DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
        model.setDataValue("....onCreate.....")
        model.data1.observe(this) { value ->
            LogUtils.d("---------value: $value")
        }

//        Glide.with(this).load()
        initContainer()
        homeBind.button1.setOnClickListener {
//            LogUtils.d("-----------${JsonUtils.toJsonStringGson(hashMap)}---")
//            hashMap.put("hashMapKey4", "new")
//            LogUtils.d("-----------${JsonUtils.toJsonStringGson(hashMap)}---")
//            handle1.sendMessage(Message.obtain(Message().apply {
//                what = 0
//            }))

        }
        homeBind.button2.setOnClickListener {}
        homeBind.button3.setOnClickListener {
            ARouter.getInstance().build(PagePath.ModuleMainPage.FLOW_PAGE).navigation()
        }
        homeBind.button4.setOnClickListener {
            ARouter.getInstance().build(PagePath.ModuleJavaPage.MAIN_PAGE).navigation()
        }

        homeBind.button5.setOnClickListener {
            homeBind.text1.text = StorageUtils1.queryStorage()

        }

        homeBind.myViewGroup.setOnClickListener {
//            LogUtils.d("--------myViewGroup-----点击事件处理---")
        }
        homeBind.myView.setOnClickListener {
//            LogUtils.d("--------myView-----点击事件处理---")

        }
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d(
            "----------activity------dispatchTouchEvent-------${
                when (event?.action) {
                    0 -> "--ACTION_DOWN事件--"
                    1 -> "--ACTION_UP事件--"
                    2 -> "--ACTION_MOVE事件--"
                    else -> "--其他事件--"
                }
            }--"
        )
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d("----------activity------onTouchEvent-------")
        return super.onTouchEvent(event)
    }

    inner class MyBroadCastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Thread.sleep(40 * 1000)
            LogUtils.d("---------MyBroadCastReceiver----onReceive----${Thread.currentThread().name}-")
        }
    }

    private fun initContainer() {
        hashMap.put("hashMapKey1", "hashMapValue1")
        hashMap.put("hashMapKey2", "hashMapValue2")
        hashMap.put("hashMapKey3", "hashMapValue3")
        hashMap.put("hashMapKey4", "hashMapValue4")

        linkHashMap.put("linkHashMapKey1", "linkHashMapValue1")
        linkHashMap.put("linkHashMapKey2", "linkHashMapValue2")
        linkHashMap.put("linkHashMapKey3", "linkHashMapValue3")
        linkHashMap.put("linkHashMapKey4", "linkHashMapValue4")

        linkHashMapAccessOrderFalse.put(
            "linkHashMapAccessOrderFalseKey1", "linkHashMapAccessOrderFalseValue1"
        )
        linkHashMapAccessOrderFalse.put(
            "linkHashMapAccessOrderFalseKey2", "linkHashMapAccessOrderFalseValue2"
        )
        linkHashMapAccessOrderFalse.put(
            "linkHashMapAccessOrderFalseKey3", "linkHashMapAccessOrderFalseValue3"
        )
        linkHashMapAccessOrderFalse.put(
            "linkHashMapAccessOrderFalseKey4", "linkHashMapAccessOrderFalseValue4"
        )

        arryList.add("arryList-00条目")
        arryList.add("arryList-11条目")
        arryList.add("arryList-22条目")
        arryList.add("arryList-33条目")
        arryList.add("arryList-44条目")
        arryList.add("arryList-55条目")
        arryList.add("arryList-66条目")




        linkList.add("linkList-00条目")
        linkList.add("linkList-11条目")
        linkList.add("linkList-22条目")
        linkList.add("linkList-33条目")
        linkList.add("linkList-44条目")
        linkList.add("linkList-55条目")
        linkList.add("linkList-66条目")

        hashSet.add("hashSet-00条目")
        hashSet.add("hashSet-00条目")
        hashSet.add("hashSet-01条目")
        hashSet.add("hashSet-02条目")
    }
}