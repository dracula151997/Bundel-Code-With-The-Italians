package com.dracula.bundelcodewiththeitalians

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
import androidx.compose.ui.res.stringResource

@Composable
internal fun OnBoardingScreen(
    viewModel: OnBoardingViewModel,
    onSettingBtnClicked: () -> Unit,
    onDismissClicked: () -> Unit,
) {
    val state by viewModel.state.collectAsState(initial = OnBoardingViewModel.State(false))
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
