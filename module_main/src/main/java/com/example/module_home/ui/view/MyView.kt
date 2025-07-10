package com.example.module_home.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import com.example.common.LogUtils

class MyView : View {
    @JvmOverloads
    constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : super(context, attributeSet, defStyleAttr)

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d("----------MyView------dispatchTouchEvent-------")
        return super.dispatchTouchEvent(event)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d("----------MyView------onTouchEvent-------")
        return super.onTouchEvent(event)
    }
}