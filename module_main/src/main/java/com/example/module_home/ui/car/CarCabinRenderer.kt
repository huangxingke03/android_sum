package com.example.module_home.ui.car

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import android.opengl.GLUtils
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import com.example.module_utils.R
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class CarCabinRenderer(private val context: Context) : GLSurfaceView.Renderer {

    private var programId = 0
    private var baseTextureId = 0
    private var maskTextureId = 0

    private var tintColor = floatArrayOf(1f, 0f, 0f, 1f) // 默认红色
    //透明色
    private val parseColor = Color.parseColor("#00000000")

    private val vertexData = floatArrayOf(
        -1f, -1f, 0f, 1f, // 左下
        1f, -1f, 1f, 1f, // 右下
        -1f, 1f, 0f, 0f, // 左上
        1f, 1f, 1f, 0f  // 右上
    )
    private val vertexBuffer: FloatBuffer =
        ByteBuffer.allocateDirect(vertexData.size * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer().apply { put(vertexData).position(0) }

    @DrawableRes
    private var baseRes = com.example.module_home.R.drawable.car_cabin_base
    @DrawableRes
    private var maskRes = com.example.module_home.R.drawable.car_cabin_mask

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        //glClearColor(0f, 0f, 0f, 1f)
        glClearColor(
            parseColor.red / 255f,
            parseColor.green / 255f,
            parseColor.blue / 255f,
            parseColor.alpha / 255f
        )
        programId = createProgram()
        glUseProgram(programId)

        baseTextureId = loadTexture(baseRes)
        maskTextureId = loadTexture(maskRes)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        glClear(GL_COLOR_BUFFER_BIT)

        val aPos = glGetAttribLocation(programId, "aPosition")
        val aTex = glGetAttribLocation(programId, "aTexCoord")
        val uBase = glGetUniformLocation(programId, "uBaseTex")
        val uMask = glGetUniformLocation(programId, "uMaskTex")
        val uColor = glGetUniformLocation(programId, "uTintColor")

        vertexBuffer.position(0)
        glVertexAttribPointer(aPos, 2, GL_FLOAT, false, 4 * 4, vertexBuffer)
        glEnableVertexAttribArray(aPos)

        vertexBuffer.position(2)
        glVertexAttribPointer(aTex, 2, GL_FLOAT, false, 4 * 4, vertexBuffer)
        glEnableVertexAttribArray(aTex)

        // Base 图
        glActiveTexture(GL_TEXTURE0)
        glBindTexture(GL_TEXTURE_2D, baseTextureId)
        glUniform1i(uBase, 0)

        // Mask 图
        glActiveTexture(GL_TEXTURE1)
        glBindTexture(GL_TEXTURE_2D, maskTextureId)
        glUniform1i(uMask, 1)

        // 染色颜色
        glUniform4fv(uColor, 1, tintColor, 0)

        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4)
    }

    fun setTintColor(@ColorInt color: Int) {
        tintColor[0] = color.red / 255f
        tintColor[1] = color.green / 255f
        tintColor[2] = color.blue / 255f
        tintColor[3] = color.alpha / 255f
    }

    fun setImages(@DrawableRes baseResId: Int, @DrawableRes maskResId: Int) {
        if (baseTextureId != 0) glDeleteTextures(1, intArrayOf(baseTextureId), 0)
        if (maskTextureId != 0) glDeleteTextures(1, intArrayOf(maskTextureId), 0)
        baseRes = baseResId
        maskRes = maskResId
        baseTextureId = loadTexture(baseRes)
        maskTextureId = loadTexture(maskRes)
    }

    private fun createProgram(): Int {
        val vertexSource = context.assets.open("shaders/vertex_shader.glsl").bufferedReader().readText()
        val fragmentSource = context.assets.open("shaders/fragment_shader.glsl").bufferedReader().readText()
        //val fragmentSource = context.assets.open("shaders/fragment_shader_new.glsl").bufferedReader().readText()

        val vertexShader = loadShader(GL_VERTEX_SHADER, vertexSource)
        val fragmentShader = loadShader(GL_FRAGMENT_SHADER, fragmentSource)

        val program = glCreateProgram()
        glAttachShader(program, vertexShader)
        glAttachShader(program, fragmentShader)
        glLinkProgram(program)
        return program
    }

    private fun loadShader(type: Int, source: String): Int {
        val shader = glCreateShader(type)
        glShaderSource(shader, source)
        glCompileShader(shader)
        return shader
    }

    private fun loadTexture(@DrawableRes resId: Int): Int {
        val texIds = IntArray(1)
        glGenTextures(1, texIds, 0)
        val textureId = texIds[0]
        glBindTexture(GL_TEXTURE_2D, textureId)

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)

        val bitmap = BitmapFactory.decodeResource(context.resources, resId)
        GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0)
        bitmap.recycle()

        return textureId
    }
}
