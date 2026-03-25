package com.aliayali.personalfinanceapp.presentation.mapper

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.aliayali.personalfinanceapp.R
import androidx.compose.ui.graphics.Color

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

    fun icon(iconName: String): Int {
        return when (iconName) {
            "ic_cash" -> R.drawable.arrow_chart
            "ic_card" -> R.drawable.auction
            "ic_ansar" -> R.drawable.bag_dollars
            "bag_euros" -> R.drawable.bag_euros
            "bag_pounds" -> R.drawable.bag_pounds
            "bag_yens" -> R.drawable.bag_yens
            "bank" -> R.drawable.bank
            "bar_chart_fall" -> R.drawable.bar_chart_fall
            "briefcase" -> R.drawable.briefcase
            "calculator" -> R.drawable.calculator
            "coin_bitcoin" -> R.drawable.coin_bitcoin
            "gold_stack" -> R.drawable.gold_stack
            "handshake" -> R.drawable.handshake
            "id_card" -> R.drawable.id_card
            "lecture" -> R.drawable.lecture
            "line_chart" -> R.drawable.line_chart
            "phone_modern" -> R.drawable.phone_modern
            "phone_old" -> R.drawable.phone_old
            "phone_outcoming_call" -> R.drawable.phone_outcoming_call
            "pie_chart" -> R.drawable.pie_chart
            "piggy" -> R.drawable.piggy
            "presentation_line_chart" -> R.drawable.presentation_line_chart
            "rook" -> R.drawable.rook
            "safe" -> R.drawable.safe
            "target" -> R.drawable.target
            "tie" -> R.drawable.tie
            "popcorn" -> R.drawable.popcorn
            "blouse" -> R.drawable.blouse
            "snickers" -> R.drawable.snickers
            "beer" -> R.drawable.beer
            "bread" -> R.drawable.bread
            "cake" -> R.drawable.cake
            "burger" -> R.drawable.burger
            "capsule" -> R.drawable.capsule
            "doctor" -> R.drawable.doctor
            "car" -> R.drawable.car
            else -> R.drawable.coin_bitcoin
        }
    }

    @Composable
    fun color(colorName: String): Color {
        return when (colorName) {
            "primary" -> MaterialTheme.colorScheme.primary
            "onPrimary" -> MaterialTheme.colorScheme.onPrimary
            "primaryContainer" -> MaterialTheme.colorScheme.primaryContainer
            "onPrimaryContainer" -> MaterialTheme.colorScheme.onPrimaryContainer
            "secondary" -> MaterialTheme.colorScheme.secondary
            "onSecondary" -> MaterialTheme.colorScheme.onSecondary
            "secondaryContainer" -> MaterialTheme.colorScheme.secondaryContainer
            "onSecondaryContainer" -> MaterialTheme.colorScheme.onSecondaryContainer
            "error" -> MaterialTheme.colorScheme.error
            "onError" -> MaterialTheme.colorScheme.onError
            "errorContainer" -> MaterialTheme.colorScheme.errorContainer
            "onErrorContainer" -> MaterialTheme.colorScheme.onErrorContainer
            "background" -> MaterialTheme.colorScheme.background
            "onBackground" -> MaterialTheme.colorScheme.onBackground
            "surface" -> MaterialTheme.colorScheme.surface
            "onSurface" -> MaterialTheme.colorScheme.onSurface
            "surfaceVariant" -> MaterialTheme.colorScheme.surfaceVariant
            "onSurfaceVariant" -> MaterialTheme.colorScheme.onSurfaceVariant
            "outline" -> MaterialTheme.colorScheme.outline
            "outlineVariant" -> MaterialTheme.colorScheme.outlineVariant
            "scrim" -> MaterialTheme.colorScheme.scrim
            "inverseSurface" -> MaterialTheme.colorScheme.inverseSurface
            "inverseOnSurface" -> MaterialTheme.colorScheme.inverseOnSurface
            "inversePrimary" -> MaterialTheme.colorScheme.inversePrimary
            else -> MaterialTheme.colorScheme.primary
        }
    }
}