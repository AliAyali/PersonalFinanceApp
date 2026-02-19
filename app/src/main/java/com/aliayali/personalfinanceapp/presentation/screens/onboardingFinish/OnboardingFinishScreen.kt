package com.aliayali.personalfinanceapp.presentation.screens.onboardingFinish

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.navigation.NavController
import com.aliayali.personalfinanceapp.R
import com.aliayali.personalfinanceapp.navigation.NavigationScreen
import com.aliayali.personalfinanceapp.presentation.screens.onboardingFinish.components.AccountItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingFinishScreen(
    navController: NavController,
    onboardingFinishViewModel: OnboardingFinishViewModel = hiltViewModel(),
) {
    var bottomSheetState by remember { mutableStateOf(false) }
    val accounts by onboardingFinishViewModel
        .getAllAccounts()
        .collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        onboardingFinishViewModel.createDefaultAccountIfNeeded()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Spacer(Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "تدبیر مالی",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.width(7.dp))
            Image(
                modifier = Modifier.size(25.dp),
                painter = painterResource(R.drawable.ic_application),
                contentDescription = null
            )
        }
        Spacer(Modifier.height(30.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "حساب کتاب ها",
            style = MaterialTheme.typography.displaySmall,
        )
        Spacer(Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            textAlign = TextAlign.Center,
            text = "همه حساب‌هات رو یکجا داشته باش؛ بانکی یا غیر بانکی، فرقی نمی‌کنه",
            color = MaterialTheme.colorScheme.inverseSurface
        )
        Spacer(Modifier.height(20.dp))
        LazyColumn {
            items(accounts) { account ->
                AccountItem(account)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(15.dp)
                .clickable {
                    bottomSheetState = true
                },

            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "افزودن حساب کتاب جدید",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.width(15.dp))
            Icon(
                painter = painterResource(R.drawable.ic_add),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(Modifier.weight(1f))
        Button(
            onClick = {
                navController.navigate(NavigationScreen.OnboardingFinish.route) {
                    popUpTo(NavigationScreen.OnboardingFeatureB.route) { inclusive = true }
                    launchSingleTop = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = "<   !بزن بریم",
                fontWeight = FontWeight.Bold
            )
        }
        TextButton(
            onClick = {
                navController.navigate(NavigationScreen.OnboardingFeatureB.route) {
                    popUpTo(NavigationScreen.OnboardingFinish.route) { inclusive = true }
                    launchSingleTop = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "صفحه قبل",
                fontWeight = FontWeight.Bold
            )
        }
    }

    if (bottomSheetState)
        ModalBottomSheet(
            onDismissRequest = { bottomSheetState = false },
            sheetMaxWidth = 350.dp,
            containerColor = MaterialTheme.colorScheme.errorContainer
        ) {

        }
}