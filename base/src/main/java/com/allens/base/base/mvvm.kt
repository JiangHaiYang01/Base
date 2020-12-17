package com.allens.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*


abstract class BaseModel

abstract class BaseRepos


abstract class BaseVM<RE : BaseRepos> : ViewModel(), BaseVMImpl<RE> {

    lateinit var repos: RE

    override fun addRepos(repository: RE) {
        repos = repository
    }
}


interface BaseVMImpl<RE : BaseRepos> : LifecycleObserver {
    fun addRepos(repository: RE)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
    }
}


abstract class BaseMVVMFragment<V : ViewDataBinding, RE : BaseRepos, VM : BaseVM<RE>>(
    private val contentLayoutId: Int
) :
    BaseFragment(contentLayoutId), BaseMVVMImpl<RE, VM> {


    lateinit var dataBind: V
    lateinit var viewModel: VM

    val repos by lazy {
        viewModel.repos
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = createViewModel(this, createVMClass())
        viewModel.addRepos(createRepos())
        lifecycle.addObserver(viewModel)
        dataBind = DataBindingUtil.inflate(inflater, contentLayoutId, container, false)
        dataBind.lifecycleOwner = this
        return dataBind.root
    }
}

abstract class BaseMVVMActivity<V : ViewDataBinding, RE : BaseRepos, VM : BaseVM<RE>> :
    BaseActivity(), BaseMVVMImpl<RE, VM> {

    companion object {
        const val TAG = "BaseMVVMActivity"
    }

    lateinit var dataBind: V
    lateinit var viewModel: VM


    override fun doCreate() {
        viewModel = createViewModel(this, createVMClass())
        // viewModel 添加一个 repository
        viewModel.addRepos(createRepos())
        //绑定生命周期
        lifecycle.addObserver(viewModel)
        //创建ViewDataBinding
        dataBind = DataBindingUtil.setContentView(this, getContentViewId())
        //用LiveData配合DataBinding的话，要手动将生成的Binding布局类和LifecycleOwner关联起来
        dataBind.lifecycleOwner = this
        doDataBind()
        onCreate()
    }

    abstract fun onCreate()
}

interface BaseMVVMImpl<RE : BaseRepos, VM : Any> {

    fun createRepos(): RE

    fun createVMClass(): Class<VM>

    fun doDataBind()


    //创建ViewModel
    fun <VM : ViewModel> createViewModel(
        owner: ViewModelStoreOwner, cls: Class<VM>
    ): VM {
        return ViewModelProvider(owner).get(cls)
    }
}
