package com.allens.base.livedata

import android.content.Context
import androidx.lifecycle.LiveData
import com.allens.base.tools.NetworkHelper
import com.allens.base.tools.OnNetWorkListener

data class NetWorkStatus(val isMobileConn: Boolean, val isWifiConn: Boolean)

//监听网络变化的liveData
class NetWorkLiveData(private val context: Context) : LiveData<NetWorkStatus>(),
    OnNetWorkListener {

    override fun onActive() {
        NetworkHelper.register(context = context.applicationContext, listener = this)
    }

    override fun onInactive() {
//        NetworkHelper.unRegister(context.applicationContext)
    }

    override fun onNetWorkStatus(isMobileConn: Boolean, isWifiConn: Boolean) {
        value = NetWorkStatus(isMobileConn, isWifiConn)
    }
}