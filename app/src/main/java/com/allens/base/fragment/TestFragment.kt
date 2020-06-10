package com.allens.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allens.base.R
import com.allens.base.databinding.FragmentTestBinding
import com.allens.base.mvvm.BaseMVVMFragment
import com.allens.base.mvvm.BaseRepos
import com.allens.base.mvvm.BaseVM

class TestFragment : BaseMVVMFragment<FragmentTestBinding, TestRepos, TestViewModel>() {
    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        inflate: View
    ) {

    }

    override fun createRepos(): TestRepos {
        return TestRepos()
    }

    override fun createVMClass(): Class<TestViewModel> {
        return TestViewModel::class.java
    }

    override fun getContentViewId(): Int {
        return R.layout.fragment_test
    }

    override fun initBindDataBing() {
        dataBind.vm = viewModel
    }

    override fun initCreate() {
    }

    override fun initLiveData() {
    }

    override fun initOnCreate() {
    }
}


class TestRepos : BaseRepos()


class TestViewModel : BaseVM<TestRepos>()