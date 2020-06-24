package com.allens.base.tools

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager


//判断gps 状态
object GpsHelper {
    private lateinit var receiver: GpsStatusReceiver

    private lateinit var context: Context


    fun register(
        context: Context, listener: OnGPSStatusChangeListener
    ) {
        GpsHelper.context = context
        val filter = IntentFilter()
        filter.addAction(LocationManager.PROVIDERS_CHANGED_ACTION)
        receiver =
            GpsStatusReceiver(listener)
        context.registerReceiver(receiver, filter)
    }

    fun unRegister(context: Context) {
        context.unregisterReceiver(receiver)
    }


    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     * @return true 表示开启
     */
    fun isOPen(): Boolean {
        val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        val gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        val network =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        return gps || network
    }

}

private class GpsStatusReceiver(private val listener: OnGPSStatusChangeListener) :
    BroadcastReceiver() {
    override fun onReceive(
        context: Context,
        intent: Intent
    ) {
        val action = intent.action
        if (action != null)
            if (action == LocationManager.PROVIDERS_CHANGED_ACTION) {
                listener.onGpsStatusChange(getGPSState(context))
            }
    }

    /**
     * 获取ＧＰＳ当前状态
     *
     * @param context
     * @return
     */
    private fun getGPSState(context: Context): Boolean {
        val locationManager =
            (context.getSystemService(Context.LOCATION_SERVICE) as LocationManager)
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

}

interface OnGPSStatusChangeListener {
    fun onGpsStatusChange(isOpen: Boolean)
}