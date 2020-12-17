package com.allens.base.tools


import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


//设置状态栏颜色
fun Activity.setStatusBarColor(@ColorInt color: Int) {
    window.statusBarColor = color
}

//设置导航栏颜色
fun Activity.setNavigationBarColor(@ColorInt color: Int) {
    window.navigationBarColor = color
}

//设置导航栏分隔线颜色
@RequiresApi(Build.VERSION_CODES.P)
fun Activity.setNavigationBarDividerColor(@ColorInt color: Int) {
    window.navigationBarDividerColor = color
}

//隐藏导航栏
fun AppCompatActivity.hideActionBar() {
    supportActionBar?.hide()
}

//显示导航栏
fun AppCompatActivity.showActionBar() {
    supportActionBar?.show()
}

//隐藏状态栏(电池栏)
fun AppCompatActivity.hideStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        //Android 11
        val controller = window.decorView.windowInsetsController
        controller?.hide(WindowInsets.Type.statusBars())
    } else {
        // 隐藏状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}

//显示状态栏(电池栏)
fun AppCompatActivity.showStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        //Android 11
        val controller = window.decorView.windowInsetsController
        controller?.show(WindowInsets.Type.statusBars())
    } else {
        //显示状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}


//隐藏NavigationBar (此方法只是短暂隐藏 点击屏幕就会显示)
fun AppCompatActivity.hideNavigationBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        //Android 11
        val controller = window.decorView.windowInsetsController
        controller?.hide(WindowInsets.Type.navigationBars())
    } else {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
}

//显示NavigationBar
fun AppCompatActivity.showNavigationBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        //Android 11
        val controller = window.decorView.windowInsetsController
        controller?.show(WindowInsets.Type.navigationBars())
    } else {
        println("暂时不知道如何主动去清楚标记")
    }
}

//将视图延伸至导航栏区域
private fun AppCompatActivity.setFitWindows() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        //Android 11 以上 将视图延伸至导航栏区域
        window.setDecorFitsSystemWindows(false)
    } else {
        //此方法是在 api 21 Android 5.0 极其以上使用
        val flag = (
                //视图延伸至导航栏区域，导航栏显示，导航栏覆盖在视图之上
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        //视图延伸至状态栏区域，状态栏覆盖在视图顶部内容之上
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        //保持整个View稳定, 常和控制System UI悬浮、隐藏的Flags共用
                        //使View不会因为System UI的变化而重新layout
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        //需要加上之前设置的
                        or window.decorView.systemUiVisibility
                )

        window.decorView.systemUiVisibility = flag
    }
}

//设置状态栏(电池栏) 文案深色还是浅色
//true: 设置白色
//false:设置黑色
@RequiresApi(Build.VERSION_CODES.M)
fun AppCompatActivity.setStatusBarContent(light: Boolean) {
    window.decorView.systemUiVisibility =
        if (light) {
            View.SYSTEM_UI_FLAG_VISIBLE
        } else {
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } or window.decorView.systemUiVisibility
}

//透明状态栏
fun AppCompatActivity.setThemeTransparent() {
    //将视图延伸至导航栏区域
    setFitWindows()
    //设置状态栏透明
    setStatusBarColor(Color.TRANSPARENT)
    //设置导航栏透明
    setNavigationBarColor(Color.TRANSPARENT)
    //隐藏导航栏
    hideActionBar()
}

//沉寂式
fun AppCompatActivity.setThemeSilence() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val controller = window.decorView.windowInsetsController
        controller?.systemBarsBehavior =
            WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        hideStatusBar()
        hideNavigationBar()
        hideActionBar()
    } else {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        or window.decorView.systemUiVisibility
                )
        window.decorView.setOnSystemUiVisibilityChangeListener {
            setThemeSilence()
        }
    }
}