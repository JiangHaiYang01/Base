package com.allens.base.tools


import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.provider.Settings.ACTION_WIRELESS_SETTINGS
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.fragment.app.Fragment


//网络检测工具
object NetworkHelper {

    private lateinit var receiver: NetworkChangeReceiver

    private lateinit var context: Context

    fun register(context: Context, listener: OnNetWorkListener) {
        this.context = context.applicationContext
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        receiver = NetworkChangeReceiver(listener)
        context.applicationContext.registerReceiver(receiver, intentFilter)
    }

    fun unRegister(context: Context){
        unRegister(context){}
    }

    fun unRegister(context: Context, doFailed: (throwable: Throwable) -> Unit) {
        try {
            context.applicationContext.unregisterReceiver(receiver)
        } catch (throwable: Throwable) {
            doFailed(throwable)
            println("unRegister netWork failed :${throwable.message}")
        }

    }


    //判断是否连接
    fun isConnected(context: Context): Boolean {
        this.context = context.applicationContext
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isNetWorkAvailableM()
        } else {
            isNetWorkAvailableL()
        }
    }

    //判断是否是wifi连接
    fun isWifi(context: Context): Boolean {
        this.context = context.applicationContext
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isWifiM()
        } else {
            isWifiL()
        }
    }

    //判断是否为移动网络
    fun isMobile(context: Context): Boolean {
        this.context = context.applicationContext
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isMobileM()
        } else {
            isMobileL()
        }
    }


    //是否是5G 网络
    @RequiresPermission(android.Manifest.permission.READ_PHONE_STATE)
    @RequiresApi(Build.VERSION_CODES.N)
    fun is5G(context: Context): Boolean {
        this.context = context.applicationContext
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if (telephonyManager.dataNetworkType == TelephonyManager.NETWORK_TYPE_NR) {
            return true
        }
        return false
    }

    //打开网络设置界面
    fun openNetWorkSetting(context: Context) {
        val intent = Intent(ACTION_WIRELESS_SETTINGS)
        if (context is Activity) {
            context.startActivityForResult(intent, 0)
        } else if (context is Fragment) {
            context.startActivityForResult(intent, 0)
        }
    }


    //23以下 判断是否是wifi连接
    private fun isWifiL(): Boolean {
        return checkConnectTypeL(ConnectivityManager.TYPE_WIFI)
    }

    //23以上 判断是否是wifi连接
    @RequiresApi(Build.VERSION_CODES.M)
    private fun isWifiM(): Boolean {
        if (!isConnected(context)) return false
        return checkConnectType(NetworkCapabilities.TRANSPORT_WIFI)
    }


    //23以上 判断是否是手机网络
    @RequiresApi(Build.VERSION_CODES.M)
    private fun isMobileM(): Boolean {
        if (!isConnected(context)) return false
        return checkConnectType(NetworkCapabilities.TRANSPORT_CELLULAR)
    }

    //23以下 判断是否是手机网络
    private fun isMobileL(): Boolean {
        return checkConnectTypeL(ConnectivityManager.TYPE_MOBILE)
    }


    //23以上 传入网络类型 返回是否是该网络类型
    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkConnectType(transportType: Int): Boolean {
        val connectivityManager = getManager()
        val networkCapabilities = connectivityManager.activeNetwork
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            actNw.hasTransport(transportType) -> true
            else -> false
        }
    }

    //23以下 传入网络类型 返回是否是该网络类型
    private fun checkConnectTypeL(type: Int): Boolean {
        val manager = getManager()
        val activeNetworkInfo: NetworkInfo = manager.activeNetworkInfo ?: return false
        return activeNetworkInfo.type == type
    }

    //23以上 判断是否连接
    @RequiresApi(Build.VERSION_CODES.M)
    private fun isNetWorkAvailableM(): Boolean {
        val connectivityManager = getManager()
        val networkCapabilities = connectivityManager.activeNetwork
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            //wifi网络
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            //蜂窝网络
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //以太网
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    //23以下 判断是否连接
    private fun isNetWorkAvailableL(): Boolean {
        val manger = getManager()
        val info = manger.activeNetworkInfo
        return info != null && info.isConnected
    }

    //获取 ConnectivityManager
    private fun getManager(): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

}


interface OnNetWorkListener {
    fun onNetWorkStatus(isMobileConn: Boolean, isWifiConn: Boolean)
}


class NetworkChangeReceiver(private val listener: OnNetWorkListener) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        listener.onNetWorkStatus(
            isMobileConn = NetworkHelper.isMobile(context),
            isWifiConn = NetworkHelper.isWifi(context)
        )
    }
}