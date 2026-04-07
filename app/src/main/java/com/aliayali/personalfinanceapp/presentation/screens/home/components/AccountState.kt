package com.aliayali.personalfinanceapp.presentation.screens.home.components

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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aliayali.personalfinanceapp.data.local.database.model.AccountType
import com.aliayali.personalfinanceapp.presentation.components.AddAccountBottomSheet
import com.aliayali.personalfinanceapp.presentation.components.FullScreenBottomSheet
import com.aliayali.personalfinanceapp.presentation.components.Line
import com.aliayali.personalfinanceapp.presentation.components.formatCardNumber
import com.aliayali.personalfinanceapp.presentation.mapper.AccountIconMapper
import com.aliayali.personalfinanceapp.presentation.screens.home.HomeViewModel

@Composable
fun AccountState(
    id: Long = 0,
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onSave: (String, String?, String?, AccountType, String) -> Unit,
    onUpdate: (Long, String, String?, String?, AccountType, String) -> Unit,
    onDelete: (Long, String, String?, String?, AccountType, String) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val account = homeViewModel.account.value
    LaunchedEffect(id) {
        homeViewModel.getAccountById(id)
    }
    var showAddAccount by remember { mutableStateOf(false) }
    FullScreenBottomSheet(isVisible, onDismiss) {
        Column(
            Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onDismiss() }
                ) {
                    Icon(Icons.Default.Close, contentDescription = "بستن")
                }
                Text(
                    text = "حساب کتاب",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
            account?.let {
                Image(
                    painter = painterResource(
                        if (it.type == AccountType.BANK_CARD)
                            AccountIconMapper.map(account.iconName)
                        else
                            AccountIconMapper.icon(account.iconName)
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(45.dp)
                )
            }
            account?.let {
                if (it.type == AccountType.BANK_CARD)
                    Text(
                        text = account.cardNumber.toString().formatCardNumber(),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                else
                    Text(
                        text = account.name,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ريال",
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = account?.currentBalance.toString(),
                    fontWeight = FontWeight.SemiBold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 0.5.dp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(10.dp)
                    .clickable {
                        showAddAccount = true
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "ویرایش",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "تراکنش های اخیر",
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Line(1, 0)
            Text(
                text = "!هنوز هیچ دخل و خرجی ثبت نکردی",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
    AddAccountBottomSheet(
        id = id,
        isVisible = showAddAccount,
        onDismiss = { showAddAccount = false },
        onSave = { cardNumber, cardName, initialInventory, accountType, icon ->
            onSave(cardNumber, cardName, initialInventory, accountType, icon)
            showAddAccount = false
        },
        onUpdate = { id, cardNumber, cardName, initialInventory, accountType, icon ->
            onUpdate(id, cardNumber, cardName, initialInventory, accountType, icon)
            showAddAccount = false
        },
        onDelete = { id, cardNumber, cardName, initialInventory, accountType, icon ->
            onDelete(id, cardNumber, cardName, initialInventory, accountType, icon)
            showAddAccount = false
        }
    )

}
