package com.allens.base.status


class StatusKey{
    companion object
    {
        const val STATUS_NETWORK = "STATUS_NETWORK"
    }
}

data class NetWorkStatus(val isMobileConn: Boolean, val isWifiConn: Boolean)