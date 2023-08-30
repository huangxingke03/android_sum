package com.example.module_home.ui

import android.os.*
import android.util.ArraySet
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.HandlerUtils
import com.example.common.JsonUtils
import com.example.common.LogUtils
import com.example.common.content.PagePath
import com.example.common.ui.base.ARouterActivity
import com.example.module_home.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Hashtable
import java.util.LinkedHashMap
import java.util.LinkedHashSet
import java.util.LinkedList
import java.util.TreeMap
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

    //    var arryList = arrayListOf(1, 2, 3, 4, 5, 6)

    var arryList = ArrayList<String>()
    var linkList = LinkedList<String>()

    var hashMap = HashMap<String, String>()
    var hashtable = Hashtable<String, String>()
    var concurrentHashMap = ConcurrentHashMap<String, String>()
    var linkHashMap = LinkedHashMap<String, String>()

    var linkHashMapAccessOrderFalse = LinkedHashMap<String, String>(16, 0.75f, true)

    var treeMap = TreeMap<String, String>()

    var array1 = intArrayOf(1, 2, 3, 4, 5, 6)

    var array2 = intArrayOf(11, 12, 13, 14)

    var linkBlockQune = LinkedBlockingDeque<String>()
    var arrayBlockingQueue = ArrayBlockingQueue<String>(5)
    var time = 10
    var url1 = "http://pic1.win4000.com/wallpaper/c/58f8211a3a604.jpg"
    var liveData = MutableLiveData<String>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        liveData.observe(this) { string ->
            LogUtils.d("--------liveData-----$string")
        }
        initContainer()
//        val imageview1 = findViewById<ImageView>(R.id.imageview1)
//        Glide.with(this).load(url1).into(imageview1)
        findViewById<Button>(R.id.button1).setOnClickListener {
//            buttonClick()
            ARouter.getInstance().build(PagePath.ModuleCommonPage.H5_PAGE).navigation()
//            liveData.postValue("10")
//            startActivity()

        }
        findViewById<Button>(R.id.button2).setOnClickListener {
//            button2Click()
//            ARouter.getInstance().build(PagePath.ModuleKotlinPage.TEST_PAGE).navigation()
            liveData.postValue("20")
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
//            ARouter.getInstance().build(PagePath.ModuleKotlinPage.MAIN_PAGE).navigation()
//            button3Click()
            ARouter.getInstance().build(PagePath.ModuleKotlinPage.MAIN_PAGE).navigation()
            liveData.value="30"
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
//            button4Click()
//            ARouter.getInstance().build(PagePath.module_common_test_page).navigation()
            linkHashMapAccessOrderFalse.get("linkHashMapAccessOrderFalseKey3")

        }
//        lifecycle.addObserver()
    }

    private fun button4Click() {
        LogUtils.d(JsonUtils.toJsonStringGson(arryList))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun button3Click() {
        hashMap.put("hashMapKey1", "hashMapValue1")
        hashMap.put("hashMapKey2", "hashMapValue2")
        hashMap.put("hashMapKey3", "hashMapValue3")
        val hashMapValue1 = hashMap.get("hashMapKey3")
        val hashMapValue2 = hashMap.getValue("hashMapKey2")

        linkHashMap.put("linkHashMapKey1", "linkHashMapValue1")
        val value1 = hashMap["hashMapKey1"]


        hashMap.remove("hashMapValue3")
        LogUtils.d(JsonUtils.toJsonStringGson(hashMap))
        hashMap.putIfAbsent("hashMapKey2", "hashMapValue222222222222222222222")
        LogUtils.d("333333333---${JsonUtils.toJsonStringGson(hashMap)}")
        linkList.remove("linkList-44条目")
        LogUtils.d("---${JsonUtils.toJsonStringGson(linkList)}")
    }

    private fun button2Click() {
//        linkList.add("linkList条目1")
//        linkList.add("linkList条目2")
//        linkList.add("linkList条目3")
//        linkList.add("linkList条目4")
//        linkList.remove("linkList条目2")
//        linkList[2]
//        hashMap.put("hashMapKey1", "hashMapValue111111111111111111")
//        if (linkList.contains("linkList-00000条目"))
//            LogUtils.d("linkList包含linkList-00条目")
//        LogUtils.d("---------button2--------")
        Thread() {
            Looper.prepare()
            HandlerUtils.handler(Looper.myLooper()!!).post {
                LogUtils.d("---222222222---${Thread.currentThread().name}")
            }
            Looper.loop()
        }.start()
    }

    private fun buttonClick() {
//        LogUtils.d(JsonUtils.toJsonStringGson(array1))
//        System.arraycopy(array1, 0, array2, 3, 4)
//        val copyOf = Arrays.copyOf(array1, 9)
//        LogUtils.d(JsonUtils.toJsonStringGson(copyOf))
//        arryList.add("arryList条目1")
//        arryList.add("arryList条目2")
//        arryList.add("arryList条目3")
//        arryList.add("arryList条目4")
//        arryList.remove("arryList条目3")
//        arryList[0]
//        var showTime = time++
//        linkHashMap.put("linkHashMapKey$showTime", "linkHashMapValue$showTime")
//        linkHashMapAccessOrderFalse.put(
//            "linkHashMapAccessOrderFalseKey$showTime",
//            "linkHashMapAccessOrderFalseValue$showTime"
//        )
//        hashMap.put("hashMapKey$showTime", "hashMapValue$showTime")
//        val listIterator = arryList.listIterator()
//        while (listIterator.hasNext()) {
//            LogUtils.d("   ${listIterator.next()}")
//            listIterator.remove()
//        }
//        LogUtils.d("   ${JsonUtils.toJsonStringGson(listIterator)}")
//        val linkListItem = linkList.get(3)
//        arryList.add("arryList-00条目")
//        LogUtils.d(JsonUtils.toJsonStringGson(linkHashMapAccessOrderFalse))
//        linkHashMapAccessOrderFalse.get("linkHashMapAccessOrderFalseKey2")
//        LogUtils.d(JsonUtils.toJsonStringGson(hashMap))
//        arrayBlockingQueue.put("arrayBlockingQueue---item---0000")
//        val iterator = linkHashMapAccessOrderFalse.entries.iterator()
//        while (iterator.hasNext()) {
//            LogUtils.d("----------${iterator.next()}")
//        }
//        LogUtils.d(linkHashMapAccessOrderFalse)
//        HandlerUtils.mainHandler().post {
//            LogUtils.d("---11111111---${Thread.currentThread().name}")
//        }
        Observable.create { emitter ->
            emitter.onNext("1");
            emitter.onComplete()
            LogUtils.d("---发射数据-------${Thread.currentThread().name}")
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { string ->
                LogUtils.d("---收到---$string----${Thread.currentThread().name}")
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
            "linkHashMapAccessOrderFalseKey1",
            "linkHashMapAccessOrderFalseValue1"
        )
        linkHashMapAccessOrderFalse.put(
            "linkHashMapAccessOrderFalseKey2",
            "linkHashMapAccessOrderFalseValue2"
        )
        linkHashMapAccessOrderFalse.put(
            "linkHashMapAccessOrderFalseKey3",
            "linkHashMapAccessOrderFalseValue3"
        )
        linkHashMapAccessOrderFalse.put(
            "linkHashMapAccessOrderFalseKey4",
            "linkHashMapAccessOrderFalseValue4"
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