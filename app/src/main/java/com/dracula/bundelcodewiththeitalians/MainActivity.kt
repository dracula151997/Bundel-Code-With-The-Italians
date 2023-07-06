package com.dracula.bundelcodewiththeitalians

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS
import android.text.TextUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

sealed class Screen(val route: String) {
    object OnBoarding : Screen("onBoarding")
    object NotificationList : Screen("notificationList")
}


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val onBoardingViewModel by viewModels<OnBoardingViewModel>()
    private val notificationListViewModel by viewModels<NotificationsListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.OnBoarding.route){
                composable(route = Screen.OnBoarding.route){
                    OnBoardingScreen(
                        viewModel = onBoardingViewModel,
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

    override fun onStart() {
        super.onStart()
        onBoardingViewModel.checkIfNeedsNotificationsPermission(this)
        notificationListViewModel.startObserving()

    }







}
