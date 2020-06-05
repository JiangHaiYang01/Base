package com.allens.base.tools

import android.app.Activity
import android.content.*
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.provider.Settings.ACTION_WIRELESS_SETTINGS
import androidx.fragment.app.Fragment


//网络检测
//原本想 用非单利的方式   每个activity 都自行注册，但是放弃了
//原因 其实功能都一样，如果非单利模式 使用 lifecycle 那么如果多个activity 都需要注册的话 就会消耗更多的内存
object NetworkHelper {

    private lateinit var receiver: NetworkChangeReceiver

    private lateinit var context: Context

    //建议放在 application 中  使用 liveData 自行分发
    fun register(context: Context, listener: OnNetWorkListener) {
        this.context = context
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        receiver = NetworkChangeReceiver(listener)
        context.registerReceiver(receiver, intentFilter)
    }

    fun unRegister(context: Context) {
        context.unregisterReceiver(receiver)
    }

    //判断是否连接
    fun isNetworkAvailable(): Boolean {
        val manger: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manger.activeNetworkInfo
        return info != null && info.isAvailable
    }

    //判断是否是wifi连接
    fun isWifi(): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo? = cm.activeNetworkInfo ?: return false
        return activeNetworkInfo?.type == ConnectivityManager.TYPE_WIFI
    }

    //判断是否为移动网络
    fun isMobile(): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo? = cm.activeNetworkInfo ?: return false
        return activeNetworkInfo?.type == ConnectivityManager.TYPE_MOBILE
    }

    //打开网络设置界面
    fun openNetWorkSetting(activity: Activity) {
        val intent = createIntent()
        activity.startActivityForResult(intent, 0)
    }


    fun openNetWorkSetting(fragment: Fragment) {
        val intent = createIntent()
        fragment.activity?.startActivityForResult(intent, 0)
    }

    private fun createIntent(): Intent {
        return Intent(ACTION_WIRELESS_SETTINGS)
    }

}


interface OnNetWorkListener {
    fun onNetWorkStatus(isMobileConn: Boolean, isWifiConn: Boolean)
}


class NetworkChangeReceiver(private val listener: OnNetWorkListener) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) {
            return
        }
        val service: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isMobileConn: Boolean = false
        var isWifiConn: Boolean = false
        for (i in service.allNetworks) {
            val networkInfo: NetworkInfo = (service.getNetworkInfo(i) ?: return) ?: return
            when (networkInfo.type) {
                ConnectivityManager.TYPE_MOBILE -> {
                    isMobileConn = networkInfo.isConnected
                }
                ConnectivityManager.TYPE_WIFI -> {
                    isWifiConn = networkInfo.isConnected
                }
            }
        }
        listener.onNetWorkStatus(isMobileConn, isWifiConn)
    }
}