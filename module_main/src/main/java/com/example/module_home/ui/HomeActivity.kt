package com.example.module_home.ui

import android.os.*
import android.util.ArraySet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
import com.example.common.LogUtils
import com.example.common.adapter.ListAdapter
import com.example.common.content.PagePath
import com.example.common.ui.base.ARouterActivity
import com.example.module_home.R
import com.example.module_home.databinding.ActivityHomeBinding
import com.example.module_home.ui.vm.LiveDataViewModel
import java.util.*

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
        ViewModelProvider(this).get(LiveDataViewModel::class.java)
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
        val listAdapter = ListAdapter().apply {
            submitList(arrayListOf("flow", "liveData", "dataBindTest", "composeTest"))
        }
        listAdapter.setOnItemClickListener(object : OnItemClickListener<String> {
            override fun onClick(
                adapter: BaseQuickAdapter<String, *>, view: View, position: Int
            ) {
                LogUtils.d("---setOnItemClickListener--- $position")
                when (position) {
                    0 -> ARouter.getInstance().build(PagePath.ModuleMainPage.FLOW_PAGE).navigation()
                    1 -> ARouter.getInstance().build(PagePath.ModuleMainPage.LIVE_DATA_PAGE)
                        .navigation()

                    2 -> ARouter.getInstance().build(PagePath.ModuleMainPage.BIND_TEST_PAGE)
                        .navigation()

                    3 -> ARouter.getInstance().build(PagePath.ModuleMainPage.COMPOSE_TEST_PAGE)
                        .navigation()
                }
            }
        })
        homeBind.mainFunction.adapter = listAdapter
        homeBind.mainFunction.layoutManager = LinearLayoutManager(this)
    }
}