package com.allens.base.baseProvider

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.allens.base.event.LiveDataBus
import com.allens.base.impl.BaseEventImpl
import com.allens.base.impl.BaseProviderImpl
import com.allens.base.impl.BaseView
import com.allens.base.status.NetWorkStatus
import com.allens.base.status.StatusKey
import com.allens.base.tools.LogTool
import com.allens.base.tools.OnTouchHelperListener
import com.allens.base.tools.StatusBarTools
import com.allens.base.tools.TouchHelper


// Activity 基类
abstract class BaseActivity : AppCompatActivity(),
    BaseProviderImpl,
    BaseView,
    BaseEventImpl,
    OnTouchHelperListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentId = getContentViewId()
        if (contentId != -1) {
            setContentView(contentId)
        }
        initOnCreate()

        //显示沉寂式
        if (setStatusBarHide())
            StatusBarTools.setStatusBar(this)

        //网络变化
        registerNetWorkStatus()
    }


    //==============================================================================================
    // 手势识别
    //==============================================================================================

    //手势 （目前有 放大 缩小）
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return when (setGestures()) {
            true -> {
                if (ev == null) {
                    super.dispatchTouchEvent(ev)
                } else {
                    when (ev.pointerCount) {
                        1 -> super.dispatchTouchEvent(ev)
                        2 -> TouchHelper.onTouchEvent(ev, this)
                        else -> super.dispatchTouchEvent(ev)
                    }
                }


            }
            false -> {
                super.dispatchTouchEvent(ev)
            }
        }
    }

    override fun setGestures(): Boolean {
        return false
    }

    override fun onGesturesAmplification() {}

    override fun onGesturesNarrow() {}

    //==============================================================================================
    // 沉寂布局
    //==============================================================================================
    override fun setStatusBarHide(): Boolean {
        return true
    }


    //==============================================================================================
    // 网络检测
    //==============================================================================================
    override fun registerNetWorkStatus() {
        LiveDataBus.with(StatusKey.STATUS_NETWORK, NetWorkStatus::class.java)
            .observe(this, Observer {
                LogTool.i("网络变化 手机网络 ${it.isMobileConn} 无线网络 ${it.isWifiConn}")
                onNetWorkStatus(it.isMobileConn, it.isWifiConn)
            })
    }

    override fun onNetWorkStatus(isMobileConn: Boolean, isWifiConn: Boolean) {

    }


}