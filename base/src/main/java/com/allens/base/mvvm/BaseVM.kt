package com.allens.base.mvvm

import androidx.lifecycle.ViewModel
import com.allens.base.impl.MVVMImpl

abstract class BaseVM<RE : BaseRepos> : ViewModel(), MVVMImpl<RE> {


    lateinit var repos: RE

    override fun addRepos(repository: RE) {
        repos = repository
    }
}



