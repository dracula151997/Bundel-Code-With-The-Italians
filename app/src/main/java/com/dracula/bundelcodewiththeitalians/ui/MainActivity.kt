package com.dracula.bundelcodewiththeitalians.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dracula.bundelcodewiththeitalians.onboarding.OnBoardingScreen
import com.dracula.bundelcodewiththeitalians.onboarding.OnBoardingViewModel
import com.dracula.bundelcodewiththeitalians.notifications.NotificationsListScreen
import com.dracula.bundelcodewiththeitalians.notifications.NotificationsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.job

sealed class Screen(val route: String) {
    object OnBoarding : Screen("onBoarding")
    object NotificationList : Screen("notificationList")
}


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val notificationListViewModel by viewModels<NotificationsListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.OnBoarding.route){
                composable(route = Screen.OnBoarding.route){
                    OnBoardingScreen(
                        viewModel = hiltViewModel(),
                        onSettingBtnClicked = {
                            showNotificationPreferences()
                        },
                        onDismissClicked = {
                            navController.navigate(Screen.NotificationList.route)
                        }
                    )
                }
                composable(route = Screen.NotificationList.route){
                    NotificationsListScreen(
                        viewModel = notificationListViewModel
                    )
                }
            }

        }
    }

    private fun showNotificationPreferences() {
        // show notification settings for this app
        startActivity(Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS));

    }








}
