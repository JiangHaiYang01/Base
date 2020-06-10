package com.allens.base.impl

import android.content.res.Resources
import androidx.lifecycle.LifecycleObserver
import com.allens.base.baseProvider.BaseApplication
import com.allens.base.mvvm.BaseRepos

interface MVVMImpl<RE : BaseRepos> : LifecycleObserver {


    fun addRepos(repository: RE)


    // 获取状态栏高度 [BaseApplication]
    fun getStatusBarHeight(): Int {
        val resources: Resources = BaseApplication.context.resources
        val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }
}