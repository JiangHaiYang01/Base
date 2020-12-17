package com.allens.base

import android.os.Bundle
import android.view.View
import coil.load
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("url-----> ${repos.mainModel.getImageURL()}")
        dataBind.imgTest.load(repos.mainModel.getImageURL())
    }
}


class MainFragmentViewModel : BaseVM<MainFragmentRepos>() {
}

class MainFragmentModel : BaseModel(){
    fun getImageURL(): String {
//        return "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608113899221&di=bb77ef7bdbbe80b73d4cc9c4e5f9f20b&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20180912%2Fe22787e14fe74fbda026ef204f686c58.jpeg"
//        return "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608121244430&di=2564b82f1b2dbd9ea12aaab1f04c8096&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20180104%2F8f202cd67e5b4f5da169fccd8bcd1a57.jpeg"
        return "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2189535784,1855314913&fm=26&gp=0.jpg"
    }
}


class MainFragmentRepos : BaseRepos() {

    val mainModel = MainFragmentModel()

}