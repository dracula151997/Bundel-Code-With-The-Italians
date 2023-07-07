package com.dracula.bundelcodewiththeitalians.notifications

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.dracula.bundelcodewiththeitalians.utils.RunOnActivityStart

@Composable
internal fun NotificationsListScreen(
    viewModel: NotificationsListViewModel,
) {
    val state by viewModel.state.collectAsState(initial = NotificationsListViewModel.State.EMPTY)

    RunOnActivityStart(onStart = viewModel::startObserving)

    Column(Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(
                    text = "Notifications",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }
            items(state.notifications.filter { it.isNotEmpty() }) { notification ->
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .padding(top = 8.dp)
                ) {
                    Text(notification, Modifier.padding(8.dp))
                }
            }
        }
    }
}
