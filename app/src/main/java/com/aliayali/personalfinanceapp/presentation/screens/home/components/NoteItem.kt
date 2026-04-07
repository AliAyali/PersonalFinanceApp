package com.aliayali.personalfinanceapp.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliayali.personalfinanceapp.core.util.PersianDate
import com.aliayali.personalfinanceapp.data.local.database.entity.NoteEntity

@Composable
fun NoteItem(
    note: NoteEntity,
    clickable: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.09f
                ),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(15.dp)
            .clickable {
                clickable()
            },
        horizontalAlignment = Alignment.End
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = note.detail,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            textAlign = TextAlign.End
        )
        Spacer(Modifier.height(5.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = note.date,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.End
        )
    }
}