package com.aliayali.personalfinanceapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Line(
    with: Int,
    padding: Int,
) {
    Spacer(Modifier.height(padding.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(with.dp)
            .background(
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
            )
    )
    Spacer(Modifier.height(padding.dp))
}