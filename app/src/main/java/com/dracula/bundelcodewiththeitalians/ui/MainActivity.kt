package com.dracula.bundelcodewiththeitalians.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dracula.bundelcodewiththeitalians.notifications.NotificationsListScreen
import com.dracula.bundelcodewiththeitalians.notifications.util.needsNotificationsPermission
import com.dracula.bundelcodewiththeitalians.onboarding.OnBoardingScreen
import com.dracula.bundelcodewiththeitalians.ui.theme.BundelCodeWithTheItaliansTheme
import com.dracula.bundelcodewiththeitalians.utils.eventAsFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

sealed class Screen(val route: String) {
    object OnBoarding : Screen("onBoarding")
    object NotificationList : Screen("notificationList")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val needsNotificationPermission = lifecycle.eventAsFlow()
        .filter { it == Lifecycle.Event.ON_START }
        .map { needsNotificationsPermission(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            BundelCodeWithTheItaliansTheme {
                NavHost(navController = navController, startDestination = Screen.OnBoarding.route) {
                    composable(route = Screen.OnBoarding.route) {
                        val needsNotificationPermission by needsNotificationPermission.collectAsState(
                            initial = false,
                        )
                        OnBoardingScreen(
                            needsPermission = needsNotificationPermission,
                            onSettingBtnClicked = {
                                showNotificationPreferences()
                            },
                        ) {
                            navController.navigate(Screen.NotificationList.route)
                        }
                    }
                    composable(route = Screen.NotificationList.route) {
                        NotificationsListScreen(
                            lifecycle = lifecycle,
                        )
                    }
                }
            }
        }
    }

    private fun showNotificationPreferences() {
        // show notification settings for this app
        startActivity(Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS))
    }
}
