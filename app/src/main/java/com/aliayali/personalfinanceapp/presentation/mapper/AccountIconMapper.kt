package com.aliayali.personalfinanceapp.presentation.mapper

import com.aliayali.personalfinanceapp.R

object AccountIconMapper {
    fun map(iconName: String): Int {
        return when (iconName) {
            "ic_cash" -> R.drawable.ic_cash
            "ic_card" -> R.drawable.ic_card
            "ic_ansar" -> R.drawable.ic_ansar
            "ic_parsian" -> R.drawable.ic_parsian
            "ic_tosee_taavon" -> R.drawable.ic_tosee_taavon
            "ic_iranzamin" -> R.drawable.ic_iranzamin
            "ic_post" -> R.drawable.ic_post
            "ic_maskan" -> R.drawable.ic_maskan
            "ic_sina" -> R.drawable.ic_sina
            "ic_saman" -> R.drawable.ic_saman
            "ic_blu" -> R.drawable.ic_blu
            else -> R.drawable.ic_application
        }
    }
}