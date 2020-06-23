package com.allens.base.activity

import com.allens.base.R
import com.allens.base.baseProvider.BaseActivity
import kotlinx.android.synthetic.main.activity_gestures.*

//手势识别的sample
class GesturesAct :BaseActivity(){
    override fun getContentViewId(): Int {
        return R.layout.activity_gestures
    }

    override fun initOnCreate() {

    }


    //默认是不开启手势的 需要设置成true 打开
    override fun setGestures(): Boolean {
        return true
    }


    override fun onGesturesAmplification() {
        super.onGesturesAmplification()
        tv_info.text = "放大"
    }

    override fun onGesturesNarrow() {
        super.onGesturesNarrow()
        tv_info.text = "缩小"
    }




}