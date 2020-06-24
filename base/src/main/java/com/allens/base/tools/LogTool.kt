package com.allens.base.tools

import android.util.Log

object LogTool {

    private const val TAG = "MVVM"

    fun i(msg: String) {
        Log.i(TAG, msg)
    }

    fun e(msg: String) {
        Log.e(TAG, msg)
    }
}