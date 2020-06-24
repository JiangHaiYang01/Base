package com.allens.base.impl

interface BaseEventImpl {

    //==============================================================================================
    // 网络检测
    //==============================================================================================
    fun registerNetWorkStatus()

    fun onNetWorkStatus(isMobileConn: Boolean, isWifiConn: Boolean)
}