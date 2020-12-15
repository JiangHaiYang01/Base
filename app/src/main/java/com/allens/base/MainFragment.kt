package com.allens.base

import com.allens.base.base.*
import com.allens.base.databinding.FragmentMainBinding

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
}


class MainFragmentViewModel : BaseVM<MainFragmentRepos>() {
}

class MainFragmentModel : BaseModel()


class MainFragmentRepos : BaseRepos() {

    val mainModel = MainFragmentModel()

}