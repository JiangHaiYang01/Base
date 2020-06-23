package com.allens.base.fragment
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.allens.base.R
import com.allens.base.activity.LoginModel
import com.allens.base.adapters.afterTextChanged
import com.allens.base.bean.LogInBean
import com.allens.base.databinding.FragmentLoginBinding
import com.allens.base.mvvm.BaseMVVMFragment
import com.allens.base.mvvm.BaseRepos
import com.allens.base.mvvm.BaseVM
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.launch

class LoginFragment :
    BaseMVVMFragment<FragmentLoginBinding, LogInFragmentRepos, LoginFragmentViewModel>() {

    override fun createRepos(): LogInFragmentRepos {
        return LogInFragmentRepos()
    }

    override fun createVMClass(): Class<LoginFragmentViewModel> {
        return LoginFragmentViewModel::class.java
    }

    override fun getContentViewId(): Int {
        return R.layout.fragment_login
    }

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

    override fun initLiveData() {
        viewModel.loginData.observe(this, Observer {
            when (it) {
                null -> {
                    Toast.makeText(activity, "failed", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}


class LogInFragmentRepos : BaseRepos() {
    private var logInModel = LoginModel()

    suspend fun login(
        userName: String,
        pwd: String,
        loginData: MutableLiveData<LogInBean>
    ) {
        logInModel.login(userName, pwd, loginData)
    }
}


class LoginFragmentViewModel : BaseVM<LogInFragmentRepos>() {
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