package com.aliayali.personalfinanceapp.presentation.mapper

import com.aliayali.personalfinanceapp.R

object AccountIconMapper {
    fun map(iconName: String): Int {
        return when (iconName) {
            "ic_cash" -> R.drawable.ic_cash
            else -> R.drawable.ic_application
        }
    }
}