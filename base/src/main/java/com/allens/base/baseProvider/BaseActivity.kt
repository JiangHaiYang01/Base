package com.allens.base.baseProvider

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.allens.base.impl.BaseProviderImpl
import com.allens.base.tools.OnTouchHelperListener
import com.allens.base.tools.StatusBarTools
import com.allens.base.tools.TouchHelper


// Activity 基类
abstract class BaseActivity : AppCompatActivity(),
    BaseProviderImpl,
    OnTouchHelperListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentId = getContentViewId()
        if (contentId != 0) {
            setContentView(contentId)
        }
        initOnCreate()

        //显示沉寂式
        if (setStatusBarHide())
            StatusBarTools.setStatusBar(this)
    }


    //是否设置沉寂式
    open fun setStatusBarHide(): Boolean {
        return false
    }

    //手势 （目前有 放大 缩小）
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return when (ev!!.pointerCount) {
            1 -> super.dispatchTouchEvent(ev)
            2 -> TouchHelper.onTouchEvent(ev, this)
            else -> super.dispatchTouchEvent(ev)
        }
    }

    override fun onGesturesAmplification() {

    }

    override fun onGesturesNarrow() {
    }


}