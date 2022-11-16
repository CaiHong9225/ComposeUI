package com.wtt.baselib.utils

import android.text.TextUtils
import android.util.Log


object LogUtils {
    private const val TAG = "LogUtils"
    fun v(msg: String) {
        println(Log.VERBOSE, "", msg)
    }

    fun v(tag: String, msg: String) {
        println(Log.VERBOSE, tag, msg)
    }

    fun d(msg: String) {
        println(Log.DEBUG, "", msg)
    }

    fun d(tag: String, msg: String) {
        println(Log.DEBUG, tag, msg)
    }

    fun i(msg: String) {
        println(Log.INFO, "", msg)
    }

    fun i(tag: String, msg: String) {
        println(Log.INFO, tag, msg)
    }

    fun w(msg: String) {
        println(Log.WARN, "", msg)
    }

    fun w(tag: String, msg: String) {
        println(Log.WARN, tag, msg)
    }

    fun e(msg: String) {
        println(Log.ERROR, "", msg)
    }

    fun e(tag: String, msg: String) {
        println(Log.ERROR, tag, msg)
    }

    fun e(tag: String, msg: String, e: Throwable?) {
        println(
            Log.ERROR, tag, """
     $msg
     ${Log.getStackTraceString(e)}
     """.trimIndent()
        )
    }

    private fun println(priority: Int, tag: String, msg: String) {
        if (priority >= Log.DEBUG) {
            if (TextUtils.isEmpty(tag)) {
                Log.println(priority, TAG, msg)
            } else {
                Log.println(priority, TAG, "[$tag] $msg")
            }
        }
    }
}