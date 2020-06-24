package com.allens.base.baseProvider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.allens.base.event.LiveDataBus
import com.allens.base.impl.BaseEventImpl
import com.allens.base.impl.BaseProviderImpl
import com.allens.base.status.NetWorkStatus
import com.allens.base.status.StatusKey
import com.allens.base.tools.LogTool
import com.allens.base.tools.TouchHelper

abstract class BaseFragment : Fragment(),
    BaseEventImpl,
    BaseProviderImpl {
    private lateinit var inflate: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflate = inflater.inflate(getContentViewId(), container, false)
        bindView(inflater, container, savedInstanceState, inflate)
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerNetWorkStatus()
    }

    open fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        inflate: View
    ) {
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