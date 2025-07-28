package com.example.module_home.ui.view

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.common.LogUtils
import com.example.module_home.R

class MyView : TextView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)


    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d("----------MyView------dispatchTouchEvent------${
            when (event?.action) {
                0 -> "--ACTION_DOWN事件--"
                1 -> "--ACTION_UP事件--"
                2 -> "--ACTION_MOVE事件--"
                else -> "--其他事件--"
            }
        }--")
        return super.dispatchTouchEvent(event)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d("----------MyView------onTouchEvent-------")
        return super.onTouchEvent(event)
    }

}