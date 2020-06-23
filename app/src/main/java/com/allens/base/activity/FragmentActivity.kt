package com.allens.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.allens.base.R

class FragmentActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
    }
}