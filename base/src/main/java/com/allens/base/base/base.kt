package com.allens.base.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


//添加碎片
fun AppCompatActivity.addFragment(fragment: Fragment, containerViewId: Int) {
    supportFragmentManager.beginTransaction().add(containerViewId, fragment).commit()
}

//替换碎片
fun AppCompatActivity.replaceFragment(fragment: Fragment, containerViewId: Int) {
    supportFragmentManager.beginTransaction().replace(containerViewId, fragment).commit()
}

//删除碎片
fun AppCompatActivity.removeFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction().remove(fragment).commit()
}

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewId())
        doCreate()
    }

    //获取ContextId
    abstract fun getContentViewId(): Int

    //onCreate
    abstract fun doCreate()


}


abstract class BaseFragment constructor(contentLayoutId: Int) : Fragment(contentLayoutId) {

}