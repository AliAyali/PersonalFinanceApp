package com.aliayali.personalfinanceapp.presentation.screens.onboardingWelcome

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aliayali.personalfinanceapp.R
import com.aliayali.personalfinanceapp.navigation.NavigationScreen
import com.aliayali.personalfinanceapp.presentation.screens.onboardingWelcome.components.SplashItemRow

@Composable
fun OnboardingWelcomeScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(Modifier.height(30.dp))
            Text(
                text = "خوش اومدی به",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "تدبیر مالی",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.width(10.dp))
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.ic_application),
                    contentDescription = null
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            SplashItemRow(
                title = "مدیریت هوشمند دخل و خرج",
                description = "نمودار مالی و مقایسه ماهانه",
                icon = R.drawable.ic_expenses
            )
            SplashItemRow(
                title = "ثبت سریع تراکنش ها در چند ثانیه",
                description = "افزودن سریع هزینه و درآمد",
                icon = R.drawable.ic_fast
            )
            SplashItemRow(
                title = "تحلیل هزینه ها با نمودارهای دقیق",
                description = "سهم هر دسته از هزینه ها",
                icon = R.drawable.ic_chart
            )
        }
        Button(
            onClick = {
                navController.navigate(NavigationScreen.OnboardingFeatureA.route) {
                    popUpTo(NavigationScreen.OnboardingWelcome.route) { inclusive = true }
                    launchSingleTop = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = "<   شروع"
            )
        }
    }

}