package com.example.module_home.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.common.LogUtils

class MyViewGroup : LinearLayout {
    @JvmOverloads
    constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : super(context, attributeSet, defStyleAttr)

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d("----------MyViewGroup------dispatchTouchEvent----${
            when (event?.action) {
                0 -> "--ACTION_DOWN事件--"
                1 -> "--ACTION_UP事件--"
                2 -> "--ACTION_MOVE事件--"
                else -> "--其他事件--"
            }
        }--")
        return super.dispatchTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.d("----------MyViewGroup------onInterceptTouchEvent-------")
//        return true
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d("----------MyViewGroup------onTouchEvent-------")
        return super.onTouchEvent(event)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
    }
}