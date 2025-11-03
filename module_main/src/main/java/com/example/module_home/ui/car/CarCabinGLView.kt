package com.example.module_home.ui.car

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import androidx.annotation.ColorInt

class CarCabinGLView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : GLSurfaceView(context, attrs) {

    private val renderer: CarCabinRenderer

    init {
        setEGLContextClientVersion(2)
        renderer = CarCabinRenderer(context)
        setRenderer(renderer)
        renderMode = RENDERMODE_WHEN_DIRTY // 手动刷新
    }

    fun setTintColor(@ColorInt color: Int) {
        renderer.setTintColor(color)
        requestRender()
    }

    fun setImages(baseResId: Int, maskResId: Int) {
        renderer.setImages(baseResId, maskResId)
        requestRender()
    }
}
