package com.allens.base.fragment

import com.allens.base.R
import com.allens.base.baseProvider.BaseFragment
import kotlinx.android.synthetic.main.fragment_status.*

class StatusFragment :BaseFragment(){
    override fun getContentViewId(): Int {
        return R.layout.fragment_status
    }

    override fun initOnCreate() {
    }

    override fun onNetWorkStatus(isMobileConn: Boolean, isWifiConn: Boolean) {
        super.onNetWorkStatus(isMobileConn, isWifiConn)
        info.text = "fragment 网络变化 手机网络 $isMobileConn 无线网络 $isWifiConn"
    }
}