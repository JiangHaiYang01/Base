package com.allens.base.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

interface BaseMVVMProviderImpl {
    //绑定 dataBind
    fun initBindDataBing()

    //处理其他逻辑
    fun initCreate()

    //处理数据变化
    fun initLiveData()


    //创建ViewModel
    fun <VM : ViewModel> createViewModel(
        owner: ViewModelStoreOwner, cls: Class<VM>
    ): VM {
        return ViewModelProvider(owner).get(cls)
    }


}