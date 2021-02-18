package com.allens.base.livedata

import android.content.Context
import androidx.lifecycle.LiveData
import com.allens.base.tools.GpsHelper
import com.allens.base.tools.OnGPSStatusChangeListener

/***
 * 使用LiveData的特性。将GPS状态封装起来
 */
class GpsLiveData(private val context: Context) : LiveData<Boolean>(), OnGPSStatusChangeListener {

    override fun onActive() {
        GpsHelper.register(context, this)
    }

    override fun onInactive() {
        //不注销。防止单例对象失效
//        GpsHelper.unRegister(context)
    }

    override fun onGpsStatusChange(isOpen: Boolean) {
        value = isOpen
    }
}