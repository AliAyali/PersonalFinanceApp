package com.aliayali.personalfinanceapp.presentation.components

fun String.maskCardNumber(): String {
    val last8 = takeLast(11)
    return "...$last8"
}