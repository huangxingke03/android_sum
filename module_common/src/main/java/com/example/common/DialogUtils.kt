package com.example.common

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import java.lang.ref.WeakReference

object DialogUtils {

    fun normalDialog(
        weakReference: WeakReference<AppCompatActivity>,
        title: String? = "标题",
        content: String? = "内容",
        positiveClick: () -> Unit
    ) {
        val appCompatActivity = weakReference.get() ?: return
        var builder = AlertDialog.Builder(appCompatActivity)
        builder.setTitle(title)
        builder.setMessage(content)
        builder.setPositiveButton(android.R.string.ok
        ) { dialog, which -> positiveClick.invoke() }
        builder.create().show()
    }
}