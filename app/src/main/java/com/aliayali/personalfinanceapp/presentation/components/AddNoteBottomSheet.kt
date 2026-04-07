package com.aliayali.personalfinanceapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aliayali.personalfinanceapp.core.util.PersianDate
import com.aliayali.personalfinanceapp.presentation.screens.home.HomeViewModel

@Composable
fun AddNoteBottomSheet(
    id: Long = 0L,
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onInsert: (detail: String, date: String) -> Unit,
    onDelete: (id: Long, detail: String, date: String) -> Unit,
    onUpdate: (id: Long, detail: String, date: String) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    var detail by remember { mutableStateOf("") }
    var date by remember {
        mutableStateOf(
            PersianDate().getTodayPersianDate()
        )
    }
    val note = homeViewModel.note.value
    LaunchedEffect(id) {
        homeViewModel.getNoteById(id)
    }
    LaunchedEffect(note) {
        if (id != 0L)
            note?.let {
                detail = it.detail
                date = it.date
            }
        else {
            detail = ""
            date = PersianDate().getTodayPersianDate()
        }
    }
    FullScreenBottomSheet(isVisible, onDismiss) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.End
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (id != 0L)
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onDelete(id, detail, date)
                            onDismiss()
                        },
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                IconButton(onClick = { onDismiss() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            TextField(
                value = detail,
                onValueChange = { detail = it },
                placeholder = {
                    Text(
                        text = "...یادداشت جدید",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                ),
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.End,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.onBackground.copy(
                            alpha = 0.1f
                        ),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(8.dp)
            ) {
                Text(
                    text = PersianDate().getTodayPersianDate(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
                Spacer(Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = {
                    if (id != 0L)
                        onUpdate(id, detail, date)
                    else
                        onInsert(detail, date)
                    onDismiss()
                },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                enabled = detail.isNotBlank()
            ) {
                if (id != 0L)
                    Text(
                        text = "ثبت تغییرات",
                        fontWeight = FontWeight.Bold
                    )
                else
                    Text(
                        text = "ثبت یادداشت",
                        fontWeight = FontWeight.Bold
                    )
            }
        }
    }
}