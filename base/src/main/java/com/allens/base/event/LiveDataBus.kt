package com.allens.base.event

import android.os.Looper
import java.util.concurrent.ConcurrentHashMap


//简易版的 LiveDataBus
object LiveDataBus {


    private val map = ConcurrentHashMap<String, MyMutableLiveData<Any>>()


    fun <T> post(key: String, t: T) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            with(key).setValue(t)
        } else {
            with(key).postValue(t)
        }
    }

    fun <T> with(key: String, clazz: Class<T>): MyMutableLiveData<T> {
        if (!map.containsKey(key)) {
            map[key] = MyMutableLiveData()
        }
        return map[key] as MyMutableLiveData<T>
    }

    fun with(key: String): MyMutableLiveData<Any> {
        return with(key, Any::class.java)
    }


    fun remove(key: String) {
        if (map.containsKey(key)) {
            map.remove(key)
        }
    }
}