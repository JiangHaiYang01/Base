package com.allens.base

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.allens.base.base.*
import com.allens.base.databinding.ActivityMainBinding
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
//        doTest()
    }

    private fun doTest() {
        dataBind.linear.addView(MaterialButton(this).apply {
            text = "隐藏导航栏"
            setOnClickListener {
                hideActionBar()
            }
        })

        dataBind.linear.addView(MaterialButton(this).apply {
            text = "显示导航栏"
            setOnClickListener {
                showActionBar()
            }
        })

        dataBind.linear.addView(MaterialButton(this).apply {
            text = "隐藏状态栏"
            setOnClickListener {
                hideStatusBar()
            }
        })


        dataBind.linear.addView(MaterialButton(this).apply {
            text = "显示状态栏"
            setOnClickListener {
                showStatusBar()
            }
        })


        dataBind.linear.addView(MaterialButton(this).apply {
            text = "隐藏Navigation"
            setOnClickListener {
                hideNavigationBar()

                Handler(Looper.getMainLooper()).postDelayed({
                    println("触发显示 navigation")
                    showNavigationBar()
                }, 1000)
            }
        })
    }
}

class MainViewModel : BaseVM<MainRepos>() {
}

class MainModel : BaseModel()


class MainRepos : BaseRepos() {

    val mainModel = MainModel()

}