package com.allens.base.mvvm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.allens.base.baseProvider.BaseFragment
import com.allens.base.impl.BaseMVVMProviderImpl
import com.allens.base.impl.MVVMCallback

abstract class BaseMVVMFragment<V : ViewDataBinding, RE : BaseRepos, VM : BaseVM<RE>> :
    BaseFragment(),
    MVVMCallback<RE, VM>,
    BaseMVVMProviderImpl {
    lateinit var dataBind: V
    lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // 创建ViewModel
        viewModel = createViewModel(this, createVMClass())
        // viewModel 添加一个 repository
        viewModel.addRepos(createRepos())
        //绑定生命周期
        lifecycle.addObserver(viewModel)
        //创建ViewDataBinding
        dataBind = DataBindingUtil.inflate(inflater, getContentViewId(), container, false)
        //用LiveData配合DataBinding的话，要手动将生成的Binding布局类和LifecycleOwner关联起来
        dataBind.lifecycleOwner = this
        return dataBind.root
    }

    override fun initOnCreate() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindDataBing()
        initCreate()
        initLiveData()
    }

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        inflate: View
    ) {

    }

}