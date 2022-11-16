package com.wtt.baselib.utils

/**
 * @description: $
 * @author $
 * @date $ $
 * @version V1.0
 */
fun ByteArray.plus() {

}

fun String.lastChar(): Char {
    return this[this.length - 1]
}

fun String.toByteArray(): ByteArray {
    return ByteArray(length) { this[it].code.toByte() }
}
