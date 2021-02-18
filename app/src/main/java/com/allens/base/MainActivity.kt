package com.allens.base

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.allens.base.base.*
import com.allens.base.databinding.ActivityMainBinding
import com.allens.base.livedata.FpsLiveData
import com.allens.base.livedata.NetWorkLiveData
import com.allens.base.tools.*
import com.google.android.material.button.MaterialButton

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