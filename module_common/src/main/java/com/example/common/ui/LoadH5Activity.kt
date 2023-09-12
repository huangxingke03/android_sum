package com.example.common.ui

//import com.example.common.webview.MyWebView
import android.net.Uri
import android.os.Bundle
import android.webkit.*
import android.widget.Button
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.DialogUtils
import com.example.common.LogUtils
import com.example.common.content.PagePath
import com.example.common.ui.base.ARouterActivity
import com.example.module_utils.R
import java.lang.ref.WeakReference

@Route(path = PagePath.ModuleCommonPage.H5_PAGE)
class LoadH5Activity : ARouterActivity() {
    //    lateinit var mainWebView: MyWebView
    lateinit var mainWebView: WebView
    lateinit var loadH5Progress: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_h5)
        mainWebView = findViewById(R.id.main_webView)
        loadH5Progress = findViewById(R.id.load_h5_progress)

        mainWebView.settings.javaScriptEnabled = true
        mainWebView.settings.javaScriptCanOpenWindowsAutomatically = true
        //js调用安卓原生方法
        mainWebView.addJavascriptInterface(AndroidtoJs(),"test")
        mainWebView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {

                val uri = request?.url
                LogUtils.d("---------shouldOverrideUrlLoading---${uri?.scheme}--${uri?.authority}--")
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }

        mainWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                loadH5Progress.text = "$newProgress%"
                super.onProgressChanged(view, newProgress)
            }

            override fun onJsAlert(
                view: WebView?,
                url: String?,
                message: String?,
                result: JsResult?
            ): Boolean {
                DialogUtils.normalDialog(WeakReference(this@LoadH5Activity), "Alert", message) {
                    result?.confirm()
                }
                return true
            }
        }
        mainWebView.loadUrl("file:///android_asset/javascript.html")

        findViewById<Button>(R.id.start_bt1).setOnClickListener {
            mainWebView.post {
                mainWebView.evaluateJavascript(
                    "javascript:callJS1()"
                ) { value -> LogUtils.d("--------js返回数据---$value----") }
            }

        }
        findViewById<Button>(R.id.start_bt2).setOnClickListener {
            mainWebView.post {
                mainWebView.loadUrl("javascript:callJS2()")
            }
        }
    }

    // js调用安卓原生方法
    class AndroidtoJs : Any() {
        @JavascriptInterface
        fun hello(msg: String?) {
            LogUtils.d("-----hello--$msg")
        }
    }

}