package com.aliayali.personalfinanceapp.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aliayali.personalfinanceapp.R
import com.aliayali.personalfinanceapp.data.local.database.model.AccountType

@Composable
fun AddAccountBottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onSave: (String, String?, String?, AccountType, String) -> Unit,
) {
    var cardNumber by remember { mutableStateOf("") }
    var bankInfo by remember { mutableStateOf<BankInfo?>(null) }

    var cardName by remember { mutableStateOf("") }
    var initialInventory by remember { mutableStateOf("") }
    var state by remember { mutableStateOf(true) }
    var switchCardState by remember {
        mutableStateOf(false)
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
                IconButton(onClick = { onDismiss() }) {
                    Icon(Icons.Default.Close, contentDescription = "بستن")
                }
                Text(
                    text = "افزودن حساب‌کتاب",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(20.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                text = "نوع حساب کتاب"
            )
            Spacer(Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .border(
                            width = 2.dp,
                            color = if (!state) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .background(
                            if (!state) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                            else MaterialTheme.colorScheme.background
                        )
                        .padding(10.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .weight(1f)
                        .clickable { state = false },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_wallet),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "منبع دیگر"
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .border(
                            width = 2.dp,
                            color = if (state) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .background(
                            if (state) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                            else MaterialTheme.colorScheme.background
                        )
                        .clip(RoundedCornerShape(5.dp))
                        .padding(10.dp)
                        .weight(1f)
                        .clickable { state = true },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_card),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "کارت بانکی"
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            if (state) {
                TextField(
                    value = cardNumber,
                    onValueChange = { it ->
                        cardNumber = it.take(16).filter { it.isDigit() }
                        bankInfo = findBankByCard(cardNumber)
                    },
                    leadingIcon = {
                        bankInfo.let {
                            Image(
                                painter = painterResource(it?.iconRes ?: R.drawable.ic_card),
                                contentDescription = it?.name,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    },
                    label = {
                        Text(
                            text = "شماره کارت ۱۶ رقمی",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                        focusedIndicatorColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                        errorIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    )
                )
                Spacer(Modifier.height(15.dp))
                TextField(
                    value = cardName,
                    onValueChange = { cardName = it },
                    label = {
                        Text(
                            text = "نام کارت (اختیاری)",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )
                    },
                    textStyle = TextStyle(
                        textAlign = TextAlign.End
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor =
                            MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                        focusedIndicatorColor =
                            MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                        errorIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "اگر برای کارتت اسم بذاری. این نام رو به جای شماره کارت نمایش می دیم",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
                Line(1, 10)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Switch(
                        modifier = Modifier.scale(0.9f),
                        checked = switchCardState,
                        onCheckedChange = { switchCardState = it }
                    )
                    Text(
                        text = "موجودی اولیه کارت"
                    )
                }
                AnimatedVisibility(
                    visible = switchCardState,
                ) {
                    TextField(
                        value = initialInventory,
                        onValueChange = { it ->
                            initialInventory = it.filter { it.isDigit() }
                        },
                        prefix = {
                            Text(
                                text = "ريال",
                            )
                        },
                        textStyle = TextStyle(
                            textAlign = TextAlign.End
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor =
                                MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                            focusedIndicatorColor =
                                MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                            errorIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                }
            } else {

            }

            Spacer(Modifier.weight(1f))

            Button(
                onClick = {
                    onSave(
                        cardNumber,
                        cardName,
                        initialInventory,
                        if (state) AccountType.BANK_CARD else AccountType.OTHER,
                        bankInfo?.name ?: "ic_card"
                    )
                },
                enabled =
                    if (switchCardState)
                        initialInventory.isNotBlank() &&
                                cardNumber.isNotBlank() &&
                                cardNumber.length == 16
                    else cardNumber.isNotBlank() &&
                            cardNumber.length == 16,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("ثبت", fontWeight = FontWeight.Bold)
            }
        }
    }
}
