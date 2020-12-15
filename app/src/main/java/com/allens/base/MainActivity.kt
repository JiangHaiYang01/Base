package com.allens.base

import com.allens.base.base.BaseMVVMActivity
import com.allens.base.base.BaseModel
import com.allens.base.base.BaseRepos
import com.allens.base.base.BaseVM
import com.allens.base.databinding.ActivityMainBinding

class MainActivity : BaseMVVMActivity<ActivityMainBinding, MainRepos, MainViewModel>() {
    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }


    override fun createRepos(): MainRepos {
        return MainRepos()
    }

    override fun createVMClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun doDataBind() {
        dataBind.vm = viewModel
    }


    override fun onCreate() {
        addFragment(MainFragment(), dataBind.linear.id)
    }
}

class MainViewModel : BaseVM<MainRepos>() {
}

class MainModel : BaseModel()


class MainRepos : BaseRepos() {

    val mainModel = MainModel()

}