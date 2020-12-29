package com.allens.base.adapters

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["android:doOnTextChanged"], requireAll = false)
fun EditText.doTextChanged(action: (values: String) -> Unit) =
    doOnTextChanged { text, _, _, _ ->
        action(text.toString())
    }