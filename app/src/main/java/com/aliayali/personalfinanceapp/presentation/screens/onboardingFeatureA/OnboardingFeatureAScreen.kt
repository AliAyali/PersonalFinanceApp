package com.aliayali.personalfinanceapp.presentation.screens.onboardingFeatureA

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aliayali.personalfinanceapp.R
import com.aliayali.personalfinanceapp.navigation.NavigationScreen
import com.aliayali.personalfinanceapp.presentation.screens.onboardingFeatureA.components.ThemeOption
import com.aliayali.personalfinanceapp.presentation.theme.ThemeViewModel

@Composable
fun OnboardingFeatureAScreen(
    navController: NavController,
    themeViewModel: ThemeViewModel,
) {
    val isDarkTheme = themeViewModel.isDarkTheme.value
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
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
        Spacer(Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ThemeOption(
                image = painterResource(R.drawable.dark),
                title = "حالت تاریک",
                selected = isDarkTheme,
                onSelect = { themeViewModel.setDarkTheme(true) },
                modifier = Modifier.width(200.dp)
            )
            Spacer(Modifier.width(20.dp))
            ThemeOption(
                image = painterResource(R.drawable.light),
                title = "حالت روشن",
                selected = !isDarkTheme,
                onSelect = { themeViewModel.setDarkTheme(false) },
                modifier = Modifier.width(200.dp)
            )
        }
        Spacer(Modifier.height(30.dp))
        Button(
            onClick = {
                navController.navigate(NavigationScreen.OnboardingFeatureB.route) {
                    popUpTo(NavigationScreen.OnboardingFeatureA.route) { inclusive = true }
                    launchSingleTop = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = "<   بعدی",
                fontWeight = FontWeight.Bold
            )
        }
        TextButton(
            onClick = {
                navController.navigate(NavigationScreen.OnboardingWelcome.route) {
                    popUpTo(NavigationScreen.OnboardingFeatureA.route) { inclusive = true }
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
}