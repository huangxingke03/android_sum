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
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.HandlerUtils
import com.example.common.JsonUtils
import com.example.common.LogUtils
import com.example.common.StorageUtils1
import com.example.common.content.PagePath
import com.example.common.ui.base.ARouterActivity
import com.example.module_home.R
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
    var a = Math.random().toInt()

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
//            LogUtils.d("--------liveData-----$string")
        }

        initContainer()
//        val imageview1 = findViewById<ImageView>(R.id.imageview1)
//        Glide.with(this).load(url1).into(imageview1)
        findViewById<Button>(R.id.button1).setOnClickListener {
//            buttonClick()
//            ARouter.getInstance().build(PagePath.ModuleCommonPage.H5_PAGE).navigation()
//            liveData.postValue("10")
//            startActivity()
//            registerReceiver(MyBroadCastReceiver(), IntentFilter("test.broadcast.one"))
            LogUtils.d("--------11111111111-----")
            var newPath =
                filesDir.absolutePath + File.separator.toString() + "/button1/newPath//$a/"
            Thread() {
                while (true) {
                    copyAssetsDir2Phone(this@HomeActivity, "test/Blog", a++, newPath)
                }

            }.start()
            val configPath = filesDir.absolutePath + "/face/configure"


        }
        findViewById<Button>(R.id.button2).setOnClickListener {
//            button2Click()
//            ARouter.getInstance().build(PagePath.ModuleKotlinPage.TEST_PAGE).navigation()
//            liveData.postValue("20")
//            Thread(){
//            sendBroadcast(Intent().apply {
//                action="test.broadcast.one"
//            })
//            LogUtils.d("-------------发广播----${Thread.currentThread().name}-")
//
//            }.start()
            var newPath =
                filesDir.absolutePath + File.separator.toString() + "/button2/newPath/$a/"
            Thread() {
                while (true) {
                    copyAssetsDir2Phone(this@HomeActivity, "test/Blog", a++, newPath)
                }

            }.start()
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
//            button3Click()
//            ARouter.getInstance().build(PagePath.ModuleKotlinPage.MAIN_PAGE).navigation()
//            liveData.value="30"
            var newPath =
                filesDir.absolutePath + File.separator.toString() + "/button3/newPath/$a/"
            Thread() {

                while (true) {
                    copyAssetsDir2Phone(this@HomeActivity, "test/Blog", a++, newPath)
                }

            }.start()
        }
        findViewById<Button>(R.id.button4).setOnClickListener {

            var newPath =
                filesDir.absolutePath + File.separator.toString() + "/button4/newPath/$a/"
            Thread() {

                while (true) {
                    copyAssetsDir2Phone(this@HomeActivity, "test/Blog", a--, newPath)
                }

            }.start()

        }
        val text1 = findViewById<TextView>(R.id.text1)
        findViewById<Button>(R.id.button5).setOnClickListener {
            text1.text = StorageUtils1.queryStorage()

        }
//        lifecycle.addObserver()
    }

    override fun onStop() {
        super.onStop()
        var newPath =
            filesDir.absolutePath + File.separator.toString() + "/onsTop1/newPath/"
        Thread() {
            var a = 5
            while (true) {
                copyAssetsDir2Phone(this@HomeActivity, "test/Blog", a++, newPath)
            }

        }.start()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.d("----------activity------dispatchTouchEvent-------")
        return super.dispatchTouchEvent(ev)
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

    fun copyAssetsDir2Phone(activity: Activity, filePath: String, a: Int, filePath1: String) {
        var filePath = filePath
        try {
            val fileList = activity.getAssets().list(filePath)
            if (fileList != null) {
                if (fileList.size > 0) { //如果是目录
                    val file = File(
                        filePath1
                    )
                    file.mkdirs() //如果文件夹不存在，则递归
                    if (fileList != null) {
                        for (fileName in fileList) {
                            copyAssetsDir2Phone(
                                activity,
                                "$filePath/$fileName",
                                a,
                                "$filePath1/$a$fileName"
                            )
                        }
                    }
                } else { //如果是文件
                    val inputStream = activity.assets.open(filePath)
                    val file = File(
                        filePath1
                    )
                    LogUtils.d("copyAssets2Phone-----file:$file")
                    val fos = FileOutputStream(file)
                    var len = -1
                    val buffer = ByteArray(1024)
                    while (inputStream.read(buffer).also { len = it } != -1) {
                        fos.write(buffer, 0, len)
                    }
                    fos.flush()
                    inputStream.close()
                    fos.close()
                    LogUtils.d("------复制完毕------")
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            LogUtils.d("------失败----${e.message}--")
        }
    }


    fun copyAssetsFile2Phone(activity: Activity, fileName: String) {
        try {
            val inputStream = activity.getAssets().open(fileName)
            //getFilesDir() 获得当前APP的安装路径 /data/data/包名/files 目录
            val file = File(
                activity.getFilesDir().getAbsolutePath() + File.separator.toString() + fileName
            )
            if (!file.exists() || file.length() === 0L) {
                val fos = FileOutputStream(file) //如果文件不存在，FileOutputStream会自动创建文件
                var len = -1
                val buffer = ByteArray(1024)
                while (inputStream.read(buffer).also { len = it } != -1) {
                    fos.write(buffer, 0, len)
                }
                fos.flush() //刷新缓存区
                inputStream.close()
                fos.close()
            } else {
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @SuppressLint("CheckResult")
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