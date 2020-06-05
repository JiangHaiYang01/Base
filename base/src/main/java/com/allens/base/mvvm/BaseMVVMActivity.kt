package com.allens.base.mvvm

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.allens.base.baseProvider.BaseActivity
import com.allens.base.impl.BaseMVVMProviderImpl
import com.allens.base.impl.MVVMCallback

abstract class BaseMVVMActivity<V : ViewDataBinding, RE : BaseRepos, VM : BaseVM<RE>> :
    BaseActivity(),
    MVVMCallback<RE, VM>,
    BaseMVVMProviderImpl {

    lateinit var dataBind: V
    lateinit var viewModel: VM

    override fun initOnCreate() {
        // 创建ViewModel
        viewModel = createViewModel(this, createVMClass())
        // viewModel 添加一个 repository
        viewModel.addRepos(createRepos())
        //绑定生命周期
        lifecycle.addObserver(viewModel)
        //创建ViewDataBinding
        dataBind = DataBindingUtil.setContentView(this, getContentViewId())
        //用LiveData配合DataBinding的话，要手动将生成的Binding布局类和LifecycleOwner关联起来
        dataBind.lifecycleOwner = this

        initBindDataBing()
        initCreate()
        initLiveData()
    }


}

