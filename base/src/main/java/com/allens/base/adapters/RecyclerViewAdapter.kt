package com.allens.base.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


//适配器拓展
@BindingAdapter("adapter")
fun RecyclerView.adapter(adapter: RecyclerView.Adapter<*>) {
    setAdapter(adapter)
}


