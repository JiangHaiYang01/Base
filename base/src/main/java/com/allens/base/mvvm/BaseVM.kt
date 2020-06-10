package com.allens.base.mvvm

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.allens.base.baseProvider.BaseApplication
import com.allens.base.impl.MVVMImpl

abstract class BaseVM<RE : BaseRepos> : ViewModel(), MVVMImpl<RE> {


    lateinit var repos: RE


    val statusBaseHeight by lazy {
        getStatusBarHeight()
    }

    override fun addRepos(repository: RE) {
        repos = repository
    }


}



