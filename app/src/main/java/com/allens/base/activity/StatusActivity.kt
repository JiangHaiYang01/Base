package com.allens.base.activity

import com.allens.base.R
import com.allens.base.baseProvider.BaseActivity
import kotlinx.android.synthetic.main.activity_status.*

class StatusActivity :BaseActivity(){
    override fun getContentViewId(): Int {
        return R.layout.activity_status
    }

    override fun initOnCreate() {
    }

    override fun onNetWorkStatus(isMobileConn: Boolean, isWifiConn: Boolean) {
        super.onNetWorkStatus(isMobileConn, isWifiConn)
        info.text = "activity 网络变化 手机网络 $isMobileConn 无线网络 $isWifiConn"
    }


    override fun setStatusBarHide(): Boolean {
        return false
    }
}