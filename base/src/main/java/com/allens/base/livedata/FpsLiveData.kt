package com.allens.base.livedata

import androidx.lifecycle.LiveData
import com.allens.base.tools.FpsUtils

class FpsLiveData : LiveData<Int>() {
    override fun onActive() {
        super.onActive()
        FpsUtils.startMonitor {
            value = it
        }
    }

    override fun onInactive() {
        super.onInactive()
        FpsUtils.stopMonitor()
    }
}