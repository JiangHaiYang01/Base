package com.allens.base.tools


// 数组转换成16进制String
fun ByteArray.toHex() =
    this.joinToString(separator = "") { it.toInt().and(0xff).toString(16).padStart(2, '0') }

//16进制String 转换成 数组
fun String.toByteArray() =
    ByteArray(this.length / 2) { this.substring(it * 2, it * 2 + 2).toInt(16).toByte() }


//16进制转10进制
fun String.toInt10() =
    this.toInt(16)

//10进制转16进制
fun Int.toInt16() =
    this.toString(16)

//int 转成 数组
fun Int.toByteArray() =
    byteArrayOf(
        (this shr 24 and 0xFF).toByte(),
        (this shr 16 and 0xFF).toByte(),
        (this shr 8 and 0xFF).toByte(),
        (this and 0xFF).toByte()
    )


object HexHelper {

    fun byteArrayToString(byte: ByteArray): String {
        return byte.toHex()
    }

    fun hexToByteArray(hex: String): ByteArray {
        return hex.toByteArray()
    }

    fun hex16To10(hex: String): Int {
        return hex.toInt10()
    }

    fun int10ToHex(data: Int): String {
        return data.toInt16()
    }

    fun intToByteArray(data: Int): ByteArray {
        return data.toByteArray()
    }


}