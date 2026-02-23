package com.aliayali.personalfinanceapp.presentation.components

import com.aliayali.personalfinanceapp.R

data class BankInfo(val name: String, val iconRes: Int)

private val cardBins = mapOf(
    "627648" to BankInfo("ic_ansar", R.drawable.ic_ansar),
    "622106" to BankInfo("ic_parsian", R.drawable.ic_parsian),
    "585983" to BankInfo("ic_tosee_taavon تعاون", R.drawable.ic_tosee_taavon),
    "505785" to BankInfo("ic_iranzamin", R.drawable.ic_iranzamin),
    "622202" to BankInfo("ic_post", R.drawable.ic_post),
    "628023" to BankInfo("ic_maskan", R.drawable.ic_maskan),
    "627381" to BankInfo("ic_ansar", R.drawable.ic_ansar),
    "639346" to BankInfo("ic_sina", R.drawable.ic_sina),
    "505801" to BankInfo("ic_saman", R.drawable.ic_saman),
    "621986" to BankInfo("ic_blu", R.drawable.ic_blu)
)

fun findBankByCard(cardNumber: String): BankInfo? {
    if (cardNumber.length < 6) return null
    val prefix = cardNumber.take(6)
    return cardBins[prefix]
}