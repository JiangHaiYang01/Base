package com.allens.base.adapters

import android.view.View
import androidx.databinding.BindingAdapter


//view 是否显示
@BindingAdapter(value = ["visible"], requireAll = false)
fun View.isVisible(visibility: Boolean) {
    if (visibility) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}

//是否隐藏
@BindingAdapter(value = ["gone"], requireAll = false)
fun View.isGone(isGone: Boolean) {
    if (isGone) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }
}
