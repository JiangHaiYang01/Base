package com.allens.base.activity

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.allens.base.MyApplication
import com.allens.base.R
import com.allens.base.adapters.afterTextChanged
import com.allens.base.bean.LogInBean
import com.allens.base.databinding.ActivityLoginBinding
import com.allens.base.mvvm.BaseMVVMActivity
import com.allens.base.mvvm.BaseModel
import com.allens.base.mvvm.BaseRepos
import com.allens.base.mvvm.BaseVM
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch

//继承 BaseMVVMActivity
class LoginAct : BaseMVVMActivity<ActivityLoginBinding, LoginRepos, LoginViewModel>() {

    //这里的接口主要是防止忘记绑定xml
    override fun initBindDataBing() {
        dataBind.viewModel = viewModel
    }


    override fun initCreate() {
        login_btn_login.setOnClickListener {
            viewModel.login(et_phone.text.toString(), et_pwd.text.toString())
        }

        login_img_delete.setOnClickListener {
            et_phone.setText("")
        }

        et_phone.afterTextChanged {
            viewModel.userName.value = it
            checkLoginClickable()
        }

        et_pwd.afterTextChanged {
            viewModel.pwd.value = it
            checkLoginClickable()
        }

        login_img_show.setOnClickListener {
            val value = viewModel.pwdCanShow.value
            if (value != null) {
                viewModel.pwdCanShow.value = !value
            }
        }
    }


    private fun checkLoginClickable() {
        val name = viewModel.userName.value
        val pwd = viewModel.pwd.value
        viewModel.loginClickAble.value =
            !(name.isNullOrEmpty() || pwd.isNullOrEmpty())
    }


    //返回当前的布局
    override fun getContentViewId(): Int {
        return R.layout.activity_login
    }

    //返回 Repos
    override fun createRepos(): LoginRepos {
        return LoginRepos()
    }

    //创建viewModel
    override fun createVMClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    //liveData变化
    override fun initLiveData() {
        viewModel.loginData.observe(this, Observer {
            when (it) {
                null -> {
                    Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}


//继承 BaseRepos
class LoginRepos : BaseRepos() {

    private var logInModel = LoginModel()

    suspend fun login(
        userName: String,
        pwd: String,
        loginData: MutableLiveData<LogInBean>
    ) {
        logInModel.login(userName, pwd, loginData)
    }


}

//继承 BaseVM
class LoginViewModel : BaseVM<LoginRepos>() {


    var userName = MutableLiveData<String>()
    var pwd = MutableLiveData<String>()

    //是否显示密码
    var pwdCanShow = MutableLiveData<Boolean>().apply {
        this.value = false
    }

    //是否可以点击登录
    var loginClickAble = MutableLiveData<Boolean>().apply {
        this.value = false
    }

    //登录返回的data
    var loginData = MutableLiveData<LogInBean>()


    fun login(userName: String, pwd: String) {
        viewModelScope.launch {
            repos.login(userName, pwd, loginData)
        }
    }

}


//继承 BaseModel
class LoginModel : BaseModel() {


    //这里是携程的方式  自己封装了一个网络请求框架
    suspend fun login(
        userName: String,
        pwd: String,
        loginData: MutableLiveData<LogInBean>
    ) {

        MyApplication.rxHttp
            .create()
            .addParameter("username", userName)
            .addParameter("password", pwd)
            .doPost("user/login", LogInBean::class.java)
            .result({ loginData.value = it }, { loginData.value = null })
    }
}
