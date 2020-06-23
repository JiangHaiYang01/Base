package com.allens.base.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.allens.base.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_GoToLogin.setOnClickListener {
            startActivity(Intent(this, LoginAct::class.java))
        }

        btn_GoToFragment.setOnClickListener {
            startActivity(Intent(this, FragmentActivity::class.java))
        }
    }


}
