package com.aliayali.personalfinanceapp.presentation.screens.home

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliayali.personalfinanceapp.R
import com.aliayali.personalfinanceapp.data.local.database.entity.AccountEntity
import com.aliayali.personalfinanceapp.presentation.components.AddAccountBottomSheet
import com.aliayali.personalfinanceapp.presentation.screens.home.components.AccountItem
import com.aliayali.personalfinanceapp.presentation.screens.home.components.AccountState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val accounts by homeViewModel
        .getAllAccounts()
        .collectAsState(initial = emptyList())
    var id by remember { mutableLongStateOf(0L) }
    var showAccountState by remember { mutableStateOf(false) }
    var showAddAccount by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.primaryContainer
            ),
    ) {
        Column(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.primary
                )
                .padding(10.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(8.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ريال",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.background
                    )
                    Text(
                        text = "0",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.background
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_balance),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                reverseLayout = true
            ) {
                items(accounts) { account ->
                    AccountItem(account) {
                        id = account.id
                        showAccountState = true
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .padding(5.dp)
                            .background(
                                color = MaterialTheme.colorScheme.background.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .padding(horizontal = 15.dp, vertical = 20.dp)
                            .clickable {
                                showAddAccount = true
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "افزودن حساب کتاب",
                            color = MaterialTheme.colorScheme.background
                        )
                        Spacer(Modifier.width(10.dp))
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }
            }
            Spacer(Modifier.height(30.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(10.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "امروز",
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.width(5.dp))
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "یک شنبه، 16 فروردین",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
            Spacer(Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(5.dp)
                        .background(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.09f),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "قسط",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.width(3.dp))
                    Icon(
                        painter = painterResource(R.drawable.ic_add_parity),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(5.dp)
                        .background(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.09f),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "چک",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.width(3.dp))
                    Icon(
                        painter = painterResource(R.drawable.ic_add_slap),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(5.dp)
                        .background(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.09f),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "یادداشت",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.width(3.dp))
                    Icon(
                        painter = painterResource(R.drawable.ic_add_note),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
    AccountState(
        id = id,
        isVisible = showAccountState,
        onDismiss = { showAccountState = false },
        onSave = { cardNumber, cardName, initialInventory, accountType, icon ->
            coroutineScope.launch {
                homeViewModel.insert(
                    AccountEntity(
                        id = 0,
                        name = cardName.toString(),
                        type = accountType,
                        cardNumber = cardNumber,
                        iconName = icon,
                        initialBalance = initialInventory?.toLongOrNull() ?: 0L
                    )
                )
            }
            showAccountState = false
        },
        onUpdate = { id, cardNumber, cardName, initialInventory, accountType, icon ->
            coroutineScope.launch {
                homeViewModel.update(
                    AccountEntity(
                        id = id,
                        name = cardName.toString(),
                        type = accountType,
                        cardNumber = cardNumber,
                        iconName = icon,
                        initialBalance = initialInventory?.toLongOrNull() ?: 0L
                    )
                )
            }
            showAccountState = false
        },
        onDelete = { id, cardNumber, cardName, initialInventory, accountType, icon ->
            coroutineScope.launch {
                homeViewModel.delete(
                    AccountEntity(
                        id = id,
                        name = cardName.toString(),
                        type = accountType,
                        cardNumber = cardNumber,
                        iconName = icon,
                        initialBalance = initialInventory?.toLongOrNull() ?: 0L
                    )
                )
            }
            showAccountState = false
        }
    )
    AddAccountBottomSheet(
        id = id,
        isVisible = showAddAccount,
        onDismiss = { showAddAccount = false },
        onSave = { cardNumber, cardName, initialInventory, accountType, icon ->
            coroutineScope.launch {
                homeViewModel.insert(
                    AccountEntity(
                        id = 0,
                        name = cardName.toString(),
                        type = accountType,
                        cardNumber = cardNumber,
                        iconName = icon,
                        initialBalance = initialInventory?.toLongOrNull() ?: 0L,
                        currentBalance = initialInventory?.toLongOrNull() ?: 0L,
                    )
                )
            }
            showAddAccount = false
        },
        onUpdate = { id, cardNumber, cardName, initialInventory, accountType, icon ->
            coroutineScope.launch {
                homeViewModel.update(
                    AccountEntity(
                        id = id,
                        name = cardName.toString(),
                        type = accountType,
                        cardNumber = cardNumber,
                        iconName = icon,
                        initialBalance = initialInventory?.toLongOrNull() ?: 0L,
                    )
                )
            }
            showAddAccount = false
        },
        onDelete = { id, cardNumber, cardName, initialInventory, accountType, icon ->
            coroutineScope.launch {
                homeViewModel.delete(
                    AccountEntity(
                        id = id,
                        name = cardName.toString(),
                        type = accountType,
                        cardNumber = cardNumber,
                        iconName = icon,
                        initialBalance = initialInventory?.toLongOrNull() ?: 0L
                    )
                )
            }
            showAddAccount = false
        }
    )
}