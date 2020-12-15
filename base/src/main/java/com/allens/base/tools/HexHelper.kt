package com.allens.base.tools

import kotlin.experimental.and


fun ByteArray.toString(): String {
    return "fuck"
}


object HexHelper {
    fun byteArrayToHexStr(byteArray: ByteArray): String {
        val hexArray = "0123456789ABCDEF".toCharArray()
        val hexChars = CharArray(byteArray.size * 2)
        for (j in byteArray.indices) {
            val v = (byteArray[j] and 0xFF.toByte()).toInt()
            hexChars[j * 2] = hexArray[v ushr 4]
            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
        }
        return String(hexChars)
    }
}