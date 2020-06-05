package com.allens.base.impl

import androidx.lifecycle.LifecycleObserver
import com.allens.base.mvvm.BaseRepos

interface MVVMImpl<RE : BaseRepos> : LifecycleObserver {


    fun addRepos(repository: RE)
}