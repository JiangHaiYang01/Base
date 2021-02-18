package com.allens.base

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import coil.load
import com.allens.base.base.*
import com.allens.base.databinding.FragmentMainBinding
import com.allens.base.livedata.NetWorkLiveData
import com.allens.base.tools.NetworkHelper
import com.allens.base.tools.OnNetWorkListener

class MainFragment :
    BaseMVVMFragment<FragmentMainBinding, MainFragmentRepos, MainFragmentViewModel>(R.layout.fragment_main) {
    override fun createRepos(): MainFragmentRepos {
        return MainFragmentRepos()
    }

    override fun createVMClass(): Class<MainFragmentViewModel> {
        return MainFragmentViewModel::class.java
    }

    override fun doDataBind() {
        dataBind.vm = viewModel
    }


    override fun doViewCreate(view: View, savedInstanceState: Bundle?) {
        val context= context as Context
        NetworkHelper.register(context = context,object :OnNetWorkListener{
            override fun onNetWorkStatus(isMobileConn: Boolean, isWifiConn: Boolean) {
                Log.i("allens-tag","isMobileConn:${isMobileConn} isWifiConn:${isWifiConn} from utils")
            }
        })

        NetWorkLiveData(context).observe(this,{
            Log.i("allens-tag","isMobileConn:${it.isMobileConn} isWifiConn:${it.isWifiConn} from livedata")
        })

    }
}


class MainFragmentViewModel : BaseVM<MainFragmentRepos>() {
}

class MainFragmentModel : BaseModel(){
}


class MainFragmentRepos : BaseRepos() {

    val mainModel = MainFragmentModel()

}