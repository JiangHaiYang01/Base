package com.allens.base

import com.allens.base.tools.*
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun hexHelperTest(){
//        val hexString2Bytes = HexHelper.hexToByteArray("a00004009ba80000")
//        println(HexHelper.byteArrayToString(hexString2Bytes))


//        println(10.toInt16())
//        println("a".toInt10())
        println(10.toByteArray().toHex())

    }
}