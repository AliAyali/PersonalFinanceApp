package com.aliayali.personalfinanceapp.core.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialogCompose(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (Int, Int) -> Unit,
) {
    if (showDialog) {

        val timeState = rememberTimePickerState(
            initialHour = 21,
            initialMinute = 0,
            is24Hour = true
        )

        Dialog(onDismissRequest = onDismiss) {
            Surface(
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    TimePicker(state = timeState)

                    Row {
                        TextButton(onClick = onDismiss) {
                            Text("انصراف")
                        }

                        TextButton(
                            onClick = {
                                onConfirm(
                                    timeState.hour,
                                    timeState.minute
                                )
                                onDismiss()
                            }
                        ) {
                            Text("تایید")
                        }
                    }
                }
            }
        }
    }
}