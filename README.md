[![](https://www.jitpack.io/v/JiangHaiYang01/BaseMvvM.svg)](https://www.jitpack.io/#JiangHaiYang01/BaseMvvM)

# BaseMvvM
Android 基础手脚架 


# Overview

简化开发 MVVM 框架 

# Download

## Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```
allprojects {
	repositories {
		maven { url 'https://www.jitpack.io' }
	}
}
```

## Step 2. Add the dependency

```
dependencies {
        implementation 'com.github.JiangHaiYang01:BaseMvvM:Tag'
}
```

# Usage


一个登录界面的 eg

```

class LoginAct : BaseMVVMActivity<ActivityLoginBinding, LoginRepos, LoginViewModel>() {
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
            viewModel.repos.logInModel.userName.value = it
            checkLoginClickable()
        }

        et_pwd.afterTextChanged {
            viewModel.repos.logInModel.pwd.value = it
            checkLoginClickable()
        }

        login_img_show.setOnClickListener {
            val value = viewModel.repos.logInModel.pwdCanShow.value
            if (value != null) {
                viewModel.repos.logInModel.pwdCanShow.value = !value
            }
        }
    }


    private fun checkLoginClickable() {
        val name = viewModel.repos.logInModel.userName.value
        val pwd = viewModel.repos.logInModel.pwd.value
        viewModel.repos.logInModel.loginClickAble.value =
            !(name.isNullOrEmpty() || pwd.isNullOrEmpty())
    }


    override fun getContentViewId(): Int {
        return R.layout.activity_login
    }

    override fun createRepos(): LoginRepos {
        return LoginRepos()
    }

    override fun createVMClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun initLiveData() {
        viewModel.repos.logInModel.loginData.observe(this, Observer {
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


class LoginRepos : BaseRepos() {

    var logInModel = LoginModel()

    suspend fun login(userName: String, pwd: String) {
        logInModel.login(userName, pwd)
    }


}


class LoginViewModel : BaseVM<LoginRepos>() {


    fun login(userName: String, pwd: String) {
        viewModelScope.launch {
            repos.login(userName, pwd)
        }
    }

}


class LoginModel : BaseModel() {

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


    suspend fun login(userName: String, pwd: String) {

        val result = MyApplication.rxHttp
            .create()
            .addParameter("username", userName)
            .addParameter("password", pwd)
            .doPost("user/login", LogInBean::class.java)
        MyApplication.rxHttp
            .checkResult(
                result, {
                    loginData.value = it
                },
                {
                    loginData.value = null
                })

    }
}


```


# License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.  
```
