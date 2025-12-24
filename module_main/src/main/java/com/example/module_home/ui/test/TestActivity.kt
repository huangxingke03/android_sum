package com.example.module_home.ui.test

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
import com.example.common.LogUtils
import com.example.common.LogUtils.logPrint
import com.example.common.TimeUtils
import com.example.common.TimeUtils.formatTime
import com.example.common.adapter.ListAdapter
import com.example.common.content.PagePath
import com.example.module_home.R
import com.example.module_home.databinding.ActivityTestBinding
import com.example.module_home.ui.vm.LiveDataViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

@Route(path = PagePath.ModuleMainPage.TEST_PAGE)
class TestActivity : AppCompatActivity() {
    private lateinit var bind: ActivityTestBinding
    val liveDataViewModel by lazy {
        ViewModelProvider(this).get(LiveDataViewModel::class)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView<ActivityTestBinding>(this, R.layout.activity_test)
        val listAdapter = ListAdapter().apply {
            submitList(arrayListOf("单例", "测试", "log", "println"))
        }
        listAdapter.setOnItemClickListener(object : OnItemClickListener<String> {
            override fun onClick(
                adapter: BaseQuickAdapter<String, *>, view: View, position: Int
            ) {
                when (position) {
                    0 -> test1()
                    1 -> test4()
                    2 -> test4()
                    3 -> test5()
                }
            }
        })
        bind.testFunction.adapter = listAdapter
        bind.testFunction.layoutManager = LinearLayoutManager(this)

        bind.btnStartLoop.setOnClickListener {
            startLoopCopy()
        }

        bind.btnStopLoop.setOnClickListener {
            stopLoopCopy()
        }

        // 初始显示存储大小
        updateStorageSize()
    }

    val videoTasks by lazy {
        listOf(
            "videos/28.mp4" to File(filesDir, "videos/intro.mp4")
        )
    }
    private var loopJob: Job? = null
    private fun startLoopCopy() {
        if (loopJob?.isActive == true) return

        bind.btnStartLoop.isEnabled = false
        bind.btnStopLoop.isEnabled = true
        bind.tvStatus.text = "无限循环复制已启动（文件存在时复制新份）..."

        loopJob = lifecycleScope.launch(Dispatchers.IO) {
            var roundCount = 0

            while (isActive) {
                roundCount++
                var copiedThisRound = 0

                withContext(Dispatchers.Main) {
                    bind.tvStatus.text = "第 $roundCount 轮复制开始（存在时复制新份）..."
                }

                for ((assetsPath, baseTargetFile) in videoTasks) {
                    if (!isActive) break

                    val fileName = assetsPath.substringAfterLast("/")
                    val extension = if (fileName.contains(".")) ".${fileName.substringAfterLast(".")}" else ""
                    val nameWithoutExt = fileName.removeSuffix(extension)

                    // 动态生成目标文件：如果存在，自动加 _copy1、_copy2 ...
                    var targetFile = baseTargetFile
                    var copyIndex = 1
                    while (targetFile.exists()) {
                        val newFileName = "${nameWithoutExt}_copy${copyIndex}$extension"
                        targetFile = File(baseTargetFile.parentFile, newFileName)
                        copyIndex++
                    }

                    withContext(Dispatchers.Main) {
                        bind.tvCurrentFile.text = "当前: ${targetFile.name} (第 $roundCount 轮)"
                        bind.progressBar.progress = 0
                    }

                    // 确保父目录存在
                    targetFile.parentFile?.mkdirs()

                    try {
                        assets.open(assetsPath).use { input ->
                            val totalSize = input.available().toLong()
                            FileOutputStream(targetFile).use { output ->
                                val buffer = ByteArray(8 * 1024)
                                var copiedBytes: Long = 0
                                var bytesRead: Int

                                while (input.read(buffer).also { bytesRead = it } != -1) {
                                    output.write(buffer, 0, bytesRead)
                                    copiedBytes += bytesRead

                                    val progress = if (totalSize > 0) {
                                        (copiedBytes * 100 / totalSize).coerceAtMost(100).toInt()
                                    } else 100

                                    withContext(Dispatchers.Main) {
                                        bind.progressBar.progress = progress
                                    }
                                }
                                output.flush()
                            }
                        }
                        copiedThisRound++

                        withContext(Dispatchers.Main) {
                            updateStorageSize() // 实时更新存储大小
                        }
                    } catch (e: Exception) {
                        LogUtils.d("LoopCopy", "复制失败: ${targetFile.name}", e)
                    }
                }

                withContext(Dispatchers.Main) {
                    bind.tvStatus.text = "第 $roundCount 轮完成（新增 $copiedThisRound 个文件）\n循环继续..."
                }

                // 每轮间隔（避免太快爆存储）
                delay(2000)
            }
        }

        loopJob?.invokeOnCompletion {
            runOnUiThread {
                bind.btnStartLoop.isEnabled = true
                bind.btnStopLoop.isEnabled = false
                bind.tvStatus.text = "无限循环已停止"
            }
        }
    }

    private fun stopLoopCopy() {
        loopJob?.cancel()
        loopJob = null
        bind.btnStopLoop.isEnabled = true
        bind.btnStopLoop.isEnabled = false
        bind.tvStatus.text = "无限循环已手动停止"
    }

    private fun updateStorageSize() {
        val filesSize = getDirSize(filesDir)
        val cacheSize = getDirSize(cacheDir)
        val totalSizeMB = (filesSize + cacheSize) / (1024 * 1024)

        bind.tvStorageSize.text = "App 已用存储：约 ${totalSizeMB} MB"
    }

    private fun getDirSize(dir: File): Long {
        if (!dir.exists()) return 0
        var size = 0L
        dir.listFiles()?.forEach { file ->
            size += if (file.isDirectory) getDirSize(file) else file.length()
        }
        return size
    }

    fun test1() {
        val jobs = mutableListOf<Job>()
        lifecycleScope.launch {
            repeat(10) {
                val job = lifecycleScope.launch {
                    val singleTestManager = SingleTestManagerOne.instance()
                    LogUtils.d("协成 $it  ， 创建singleTestManager ， hashCode:   ${singleTestManager.hashCode()}")
                }
                jobs.add(job)
            }
            jobs.joinAll()
        }
    }

    fun test2() {
        val jobs = mutableListOf<Job>()
        lifecycleScope.launch {
            repeat(10) {
                val job = lifecycleScope.launch {
                    val singleTestManager = SingleTestManagerOne.instance()
                    LogUtils.d("协成 $it  ， 创建singleTestManager ， hashCode:   ${singleTestManager.hashCode()}")
                }
                jobs.add(job)
            }
            jobs.joinAll()
        }
    }

    fun test3() {
        val jobs = mutableListOf<Job>()
        lifecycleScope.launch {
            repeat(5) {
                val job = lifecycleScope.launch {
                    liveDataViewModel.setDataValue3("协成 ： $it --创建的")
                }
                jobs.add(job)
            }
            jobs.joinAll()
        }
    }

    fun test4() {
        lifecycleScope.launch {
            LogUtils.d("test4 delay前时间 ： ${formatTime(System.currentTimeMillis())}")
            delay(4000)
            LogUtils.d("test4 delay后时间 ： ${formatTime(System.currentTimeMillis())}")
        }
    }

    fun test5() {
        lifecycleScope.launch {
            logPrint("test5 delay前时间 ： ${formatTime(System.currentTimeMillis())}")
            delay(4000)
            logPrint("test5 delay后时间 ： ${formatTime(System.currentTimeMillis())}")
        }
    }
}