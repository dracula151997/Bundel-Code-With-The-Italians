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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.dracula.bundelcodewiththeitalians.notifications.service.BundelNotificationListenerService

@Composable
internal fun NotificationsListScreen(
    lifecycle: Lifecycle
) {
    val notifications by remember(lifecycle) {
        BundelNotificationListenerService.notificationFlow.flowWithLifecycle(lifecycle = lifecycle)
    }.collectAsState(initial = emptyList())


    Column(Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(
                    text = "Notifications",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }
            items(notifications.filter { it.isNotEmpty() }) { notification ->
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
