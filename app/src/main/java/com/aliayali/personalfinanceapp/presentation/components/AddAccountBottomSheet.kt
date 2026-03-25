package com.aliayali.personalfinanceapp.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aliayali.personalfinanceapp.R
import com.aliayali.personalfinanceapp.data.local.database.model.AccountType
import com.aliayali.personalfinanceapp.presentation.mapper.AccountIconMapper
import com.aliayali.personalfinanceapp.presentation.screens.onboardingFinish.OnboardingFinishViewModel

@Composable
fun AddAccountBottomSheet(
    id: Long = 0,
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onSave: (String, String?, String?, AccountType, String) -> Unit,
    onUpdate: (Long, String, String?, String?, AccountType, String) -> Unit,
    onDelete: (Long, String, String?, String?, AccountType, String) -> Unit,
    onboardingFinishViewModel: OnboardingFinishViewModel = hiltViewModel(),
) {
    var showEditIconBottomSheet by remember { mutableStateOf(false) }
    val account = onboardingFinishViewModel.account.value
    var cardNumber by remember { mutableStateOf("") }
    var bankInfo by remember { mutableStateOf<BankInfo?>(null) }
    var name by remember { mutableStateOf("") }
    var initialInventory by remember { mutableStateOf("") }
    var accountTypeState by remember { mutableStateOf(true) }
    var switchState by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf("") }
    error = ""
    var exists by remember { mutableStateOf(false) }
    var iconSelected by remember { mutableStateOf("bag_dollars") }

    LaunchedEffect(id) {
        onboardingFinishViewModel.getById(id)
    }
    LaunchedEffect(cardNumber) {
        if (id == 0L)
            exists = onboardingFinishViewModel.isCardNumberExists(cardNumber)
    }
    LaunchedEffect(account) {
        if (id != 0L)
            account?.let {
                accountTypeState = it.type == AccountType.BANK_CARD
                cardNumber = it.cardNumber.toString()
                name = it.name
                initialInventory = it.initialBalance.toString()
                switchState = account.initialBalance != 0L
                iconSelected = account.iconName
            }
        else {
            accountTypeState = true
            cardNumber = ""
            name = ""
            initialInventory = ""
            switchState = false
            iconSelected = "bag_dollars"
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
                IconButton(onClick = { onDismiss() }) {
                    Icon(Icons.Default.Close, contentDescription = "بستن")
                }
                if (id != 0L)
                    Text(
                        text = "ویرایش حساب‌کتاب",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                else
                    Text(
                        text = "افزودن حساب‌کتاب",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                if (id != 0L)
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onDelete(
                                id,
                                cardNumber,
                                name,
                                initialInventory,
                                if (accountTypeState) AccountType.BANK_CARD else AccountType.OTHER,
                                bankInfo?.name ?: "ic_card"
                            )
                        }
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
                            color = if (!accountTypeState) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .background(
                            if (!accountTypeState) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                            else MaterialTheme.colorScheme.background
                        )
                        .padding(10.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .weight(1f)
                        .clickable {
                            if (id == 0L)
                                accountTypeState = false
                        },
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
                            color = if (accountTypeState) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .background(
                            if (accountTypeState) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                            else MaterialTheme.colorScheme.background
                        )
                        .clip(RoundedCornerShape(5.dp))
                        .padding(10.dp)
                        .weight(1f)
                        .clickable {
                            if (id == 0L)
                                accountTypeState = true
                        },
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
            if (accountTypeState) {
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
                Spacer(Modifier.height(10.dp))
                if (error.isNotBlank()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(Modifier.width(5.dp))
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
                Spacer(Modifier.height(15.dp))
                TextField(
                    value = name,
                    onValueChange = { name = it },
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
                        checked = switchState,
                        onCheckedChange = { switchState = it }
                    )
                    Text(
                        text = "موجودی اولیه کارت"
                    )
                }
                AnimatedVisibility(
                    visible = switchState,
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box {
                        Image(
                            painter = painterResource(AccountIconMapper.icon(iconSelected)),
                            contentDescription = null,
                            modifier = Modifier
                                .size(130.dp)
                                .align(Alignment.Center)
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(30.dp)
                                .clickable {
                                    showEditIconBottomSheet = true
                                }
                        )
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(5.dp)
                                .size(30.dp)
                                .clickable {
                                    showEditIconBottomSheet = true
                                },
                        )
                    }
                }
                Spacer(Modifier.height(15.dp))
                TextField(
                    value = name,
                    onValueChange = { it ->
                        name = it
                    },
                    label = {
                        Text(
                            text = "نام منبع",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    textStyle = TextStyle(
                        textAlign = TextAlign.End
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                        focusedIndicatorColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                        errorIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    )
                )
                Spacer(Modifier.height(15.dp))
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
                        checked = switchState,
                        onCheckedChange = { switchState = it }
                    )
                    Text(
                        text = "ثبت موجودی اولیه منبع"
                    )
                }
                AnimatedVisibility(
                    visible = switchState,
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
            }

            Spacer(Modifier.weight(1f))

            if (accountTypeState)
                Button(
                    onClick = {
                        if (exists)
                            error = "قبلا این کارت رو ثبت کردی"
                        else {
                            if (id != 0L)
                                onUpdate(
                                    id,
                                    cardNumber,
                                    name,
                                    initialInventory,
                                    if (accountTypeState) AccountType.BANK_CARD else AccountType.OTHER,
                                    bankInfo?.name ?: "ic_card"
                                )
                            else
                                onSave(
                                    cardNumber,
                                    name,
                                    initialInventory,
                                    if (accountTypeState) AccountType.BANK_CARD else AccountType.OTHER,
                                    bankInfo?.name ?: "ic_card"
                                )
                        }
                    },
                    enabled =
                        if (switchState)
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
                    if (id != 0L)
                        Text("ثبت تغییرات", fontWeight = FontWeight.Bold)
                    else
                        Text("ثبت", fontWeight = FontWeight.Bold)
                }
            else
                Button(
                    onClick = {
                        if (id != 0L)
                            onUpdate(
                                id,
                                cardNumber,
                                name,
                                initialInventory,
                                AccountType.OTHER,
                                iconSelected
                            )
                        else
                            onSave(
                                cardNumber,
                                name,
                                initialInventory,
                                AccountType.OTHER,
                                iconSelected
                            )
                    },
                    enabled =
                        if (switchState) initialInventory.isNotBlank()
                        else name.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    if (id != 0L)
                        Text("ثبت تغییرات", fontWeight = FontWeight.Bold)
                    else
                        Text("ثبت", fontWeight = FontWeight.Bold)
                }
        }
        EditIconItemBottomSheet(
            isVisible = showEditIconBottomSheet,
            onDismiss = { showEditIconBottomSheet = false },
            onClickIcon = { icon ->
                iconSelected = icon
            },
        )
    }
}
