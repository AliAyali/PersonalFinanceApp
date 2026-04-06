package com.aliayali.personalfinanceapp.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliayali.personalfinanceapp.data.local.database.entity.AccountEntity
import com.aliayali.personalfinanceapp.data.local.database.model.AccountType
import com.aliayali.personalfinanceapp.presentation.components.formatCardNumber
import com.aliayali.personalfinanceapp.presentation.components.maskCardNumber
import com.aliayali.personalfinanceapp.presentation.mapper.AccountIconMapper

@Composable
fun AccountItem(
    account: AccountEntity,
    clickable: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(end = 10.dp, start = 40.dp, top = 10.dp, bottom = 10.dp)
            .clickable {
                clickable()
            },
        horizontalAlignment = Alignment.End
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (account.type == AccountType.BANK_CARD)
                Text(
                    text = account.cardNumber.toString().formatCardNumber().maskCardNumber(),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp,
                )
            else
                Text(
                    text = account.name,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            Image(
                painter = painterResource(
                    if (account.type == AccountType.BANK_CARD)
                        AccountIconMapper.map(account.iconName)
                    else
                        AccountIconMapper.icon(account.iconName)
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp),
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "ريال",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = account.currentBalance.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}