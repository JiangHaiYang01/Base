package com.allens.base.impl

interface BaseView {
    //是否设置沉寂式 默认开启
    fun setStatusBarHide(): Boolean

    //是否启动手势 默认不开启
    fun setGestures(): Boolean
}