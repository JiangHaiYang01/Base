package com.allens.base.baseProvider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.allens.base.impl.BaseProviderImpl
import com.allens.base.tools.TouchHelper

abstract class BaseFragment : Fragment(),
    BaseProviderImpl {
    private lateinit var inflate: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflate = inflater.inflate(getContentViewId(), container, false)
        bindView(inflater, container, savedInstanceState, inflate)
        return inflate
    }


    abstract fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        inflate: View
    )
}