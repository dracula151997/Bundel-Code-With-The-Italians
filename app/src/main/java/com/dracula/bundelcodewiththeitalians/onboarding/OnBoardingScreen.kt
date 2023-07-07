package com.dracula.bundelcodewiththeitalians.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.dracula.bundelcodewiththeitalians.R
import com.dracula.bundelcodewiththeitalians.utils.RunOnActivityStart

@Composable
internal fun OnBoardingScreen(
    viewModel: OnBoardingViewModel,
    onSettingBtnClicked: () -> Unit,
    onDismissClicked: () -> Unit,
) {
    val state by viewModel.state.collectAsState(initial = OnBoardingViewModel.State(false))
    val onBoardingViewModel = hiltViewModel<OnBoardingViewModel>()
    val context = LocalContext.current

    RunOnActivityStart(
        onStart = {
            onBoardingViewModel.checkIfNeedsNotificationsPermission(context)
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.needsPermission)
            RequestNotificationAccess(onSettingBtnClicked)
        else Button(onClick = onDismissClicked) {
            Text(text = stringResource(R.string.you_are_all_set))
        }
    }
}

@Composable
private fun RequestNotificationAccess(onSettingBtnClicked: () -> Unit) {
    Text(
        text = stringResource(id = R.string.bundle_code_with_the_italians),
        style = MaterialTheme.typography.headlineMedium,
    )
    Button(onClick = onSettingBtnClicked) {
        Text(text = stringResource(R.string.give_us_permission_to_read_your_notifications))
    }
}
