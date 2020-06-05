package com.allens.base.impl

interface BaseProviderImpl {
    //获取布局Id
    fun getContentViewId(): Int


    //创建布局之后
    fun initOnCreate()
}