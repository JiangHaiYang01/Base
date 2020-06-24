package com.allens.base.baseProvider

import android.app.Application
import android.content.Context
import com.allens.base.event.LiveDataBus
import com.allens.base.impl.BaseEventImpl
import com.allens.base.status.NetWorkStatus
import com.allens.base.status.StatusKey
import com.allens.base.tools.NetworkHelper
import com.allens.base.tools.OnNetWorkListener

abstract class BaseApplication : Application(),
    OnNetWorkListener,
    BaseEventImpl {


    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        //注册网络变化
        if (setNewWorkStatus())
            registerNetWorkStatus()
    }

    //==============================================================================================
    // 网络检测
    //==============================================================================================
    override fun registerNetWorkStatus() {
        NetworkHelper.register(this, this)
    }

    override fun onNetWorkStatus(isMobileConn: Boolean, isWifiConn: Boolean) {
        LiveDataBus.post(StatusKey.STATUS_NETWORK, NetWorkStatus(isMobileConn, isWifiConn))
    }

    //是否开启网络
    open fun setNewWorkStatus(): Boolean {
        return true
    }
}