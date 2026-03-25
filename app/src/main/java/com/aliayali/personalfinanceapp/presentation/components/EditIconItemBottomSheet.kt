package com.aliayali.personalfinanceapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aliayali.personalfinanceapp.presentation.mapper.AccountIconMapper

@Composable
fun EditIconItemBottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onClickIcon: (icon: String) -> Unit,
) {
    val icons = remember {
        mutableListOf(
            "ic_cash", "ic_card", "ic_ansar", "bag_euros", "bag_pounds",
            "bag_yens", "bank", "bar_chart_fall", "briefcase", "calculator",
            "coin_bitcoin", "gold_stack", "handshake", "id_card", "lecture",
            "line_chart", "phone_modern", "phone_old", "phone_outcoming_call", "pie_chart",
            "piggy", "presentation_line_chart", "rook", "safe", "target",
            "tie", "popcorn", "blouse", "snickers", "beer",
            "bread", "cake", "burger", "capsule", "doctor", "car"
        )
    }
    FullScreenBottomSheet(
        isVisible,
        onDismiss
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(10.dp),
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(icons) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            onClickIcon(it)
                            onDismiss()
                        },
                    painter = painterResource(AccountIconMapper.icon(it)),
                    contentDescription = null,
                )
            }
        }
    }
}