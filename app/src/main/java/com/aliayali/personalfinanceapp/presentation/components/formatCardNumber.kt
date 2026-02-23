package com.aliayali.personalfinanceapp.presentation.components

fun String.formatCardNumber(): String {
    return this
        .filter { it.isDigit() }
        .chunked(4)
        .joinToString(" - ")
}