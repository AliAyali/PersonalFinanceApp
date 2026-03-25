package com.aliayali.personalfinanceapp.presentation.screens.onboardingFinish.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aliayali.personalfinanceapp.data.local.database.entity.AccountEntity
import com.aliayali.personalfinanceapp.data.local.database.model.AccountType
import com.aliayali.personalfinanceapp.presentation.components.formatCardNumber
import com.aliayali.personalfinanceapp.presentation.mapper.AccountIconMapper

@Composable
fun AccountItem(
    account: AccountEntity,
    clickable: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(10.dp)
            .clickable {
                clickable()
            },

        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = account.name.ifBlank {
                account.cardNumber?.formatCardNumber() ?: ""
            },
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.width(15.dp))
        Image(
            painter = painterResource(
                if (account.type == AccountType.BANK_CARD)
                    AccountIconMapper.map(account.iconName)
                else
                    AccountIconMapper.icon(account.iconName)
            ),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 12.dp),
        )
    }
}