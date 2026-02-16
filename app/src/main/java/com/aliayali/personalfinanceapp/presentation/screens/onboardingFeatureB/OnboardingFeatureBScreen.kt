package com.aliayali.personalfinanceapp.presentation.screens.onboardingFeatureB

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliayali.personalfinanceapp.R
import com.aliayali.personalfinanceapp.core.util.TimePickerDialogCompose
import com.aliayali.personalfinanceapp.core.util.notification.RequestNotificationPermission
import com.aliayali.personalfinanceapp.navigation.NavigationScreen
import com.aliayali.personalfinanceapp.presentation.screens.notification.NotificationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingFeatureBScreen(
    navController: NavController,
    onboardingFeatureBViewModel: OnboardingFeatureBViewModel = hiltViewModel(),
    notificationViewModel: NotificationViewModel = hiltViewModel(),
) {
    RequestNotificationPermission()
    var showTimePicker by remember { mutableStateOf(false) }
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
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "یادآور",
            style = MaterialTheme.typography.displaySmall,
        )
        Spacer(Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            textAlign = TextAlign.Center,
            text = "با فعال کردن یادآور، تدبیر مالی روزانه" +
                    " سر ساعت مشخص برای ثبت تراکنش ها یادآوری میکنه",
            color = MaterialTheme.colorScheme.inverseSurface
        )
        Spacer(Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .border(
                    width = 0.5.dp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(
                    modifier = Modifier.scale(0.9f),
                    checked = onboardingFeatureBViewModel.dailyStatus,
                    onCheckedChange = { enabled ->
                        onboardingFeatureBViewModel.setDailyStatus(enabled)
                    },
                )
                Text(
                    text = "یادآور روزانه"
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier.padding(5.dp),
                    onClick = {
                        showTimePicker = true
                    }
                ) {
                    Text(
                        text = onboardingFeatureBViewModel.selectedTime,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "زمان مدنظر شما"
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                val (hour, minute) = onboardingFeatureBViewModel.getSelectedTime()
                if (onboardingFeatureBViewModel.dailyStatus) {
                    notificationViewModel.updateNotificationTime(hour, minute)
                } else {
                    notificationViewModel.cancelDailyNotification()
                }
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
                text = "<   بعدی",
                fontWeight = FontWeight.Bold
            )
        }
        TextButton(
            onClick = {
                navController.navigate(NavigationScreen.OnboardingFeatureA.route) {
                    popUpTo(NavigationScreen.OnboardingFeatureB.route) { inclusive = true }
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
    TimePickerDialogCompose(
        showDialog = showTimePicker,
        onDismiss = { showTimePicker = false },
        onConfirm = { h, m ->
            onboardingFeatureBViewModel.onTimeSelected(h, m)
            if (onboardingFeatureBViewModel.dailyStatus) {
                notificationViewModel.updateNotificationTime(h, m)
            }
        }
    )
}