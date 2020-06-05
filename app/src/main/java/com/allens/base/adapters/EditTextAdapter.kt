package com.allens.base.adapters

import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import androidx.databinding.BindingAdapter


@BindingAdapter(value = ["afterTextChanged"])
fun EditText.afterTextChanged(action: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            action(s.toString())
        }
    })
}


//密码是否可见
@BindingAdapter(
    value = ["transformationMethod"],
    requireAll = false
)
fun EditText.transformationMethod(isSHowPwd: Boolean) {
    if (isSHowPwd) {
        this.transformationMethod = HideReturnsTransformationMethod.getInstance()
    } else {
        this.transformationMethod = PasswordTransformationMethod.getInstance()
    }
    //让光标移动到最后
    setSelection(text.toString().trim().length)
}
