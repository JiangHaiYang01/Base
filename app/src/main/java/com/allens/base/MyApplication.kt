package com.allens.base

import android.app.Application
import com.allens.base.baseProvider.BaseApplication
import com.allens.lib_http2.RxHttp
import com.allens.lib_http2.config.HttpLevel

class MyApplication : BaseApplication() {

    companion object {
        lateinit var rxHttp: RxHttp
    }

    override fun onCreate() {
        super.onCreate()
        rxHttp = RxHttp.Builder()
            .baseUrl("https://www.wanandroid.com")
            .isLog(true)
            .level(HttpLevel.BODY)
            .writeTimeout(10)
            .readTimeout(10)
            .connectTimeout(10)
            .build(this)
    }
}