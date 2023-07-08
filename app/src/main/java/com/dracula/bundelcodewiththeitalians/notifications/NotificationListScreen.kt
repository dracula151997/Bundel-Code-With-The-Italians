package com.dracula.bundelcodewiththeitalians.notifications

import android.content.Context
import android.graphics.drawable.Icon
import android.text.format.DateUtils
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.dracula.bundelcodewiththeitalians.R
import com.dracula.bundelcodewiththeitalians.notifications.service.BundelNotificationListenerService

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun NotificationsListScreen(
    lifecycle: Lifecycle,
) {
    val notifications by remember(lifecycle) {
        BundelNotificationListenerService.notificationFlow.flowWithLifecycle(lifecycle = lifecycle)
    }.collectAsState(initial = emptyList())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NotificationsListTopAppBar()
        },
    ) { contentPadding ->
        if (notifications.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = contentPadding,
            ) {
                item {
                    Text(
                        text = stringResource(R.string.notifications_screen_title),
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(8.dp),
                    )
                }
                stickyHeader {
                }
                items(notifications) { notification ->
                    NotificationItem(notification)
                }
            }
        } else {
            Text(text = "No notifications", Modifier.padding(8.dp))
        }
    }
}

@Composable
private fun NotificationItem(notification: Notification) {
    ElevatedCard(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White,
        ),
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                NotificationIcon(notification)
                Spacer(modifier = Modifier.width(8.dp))
                Text(notification.text ?: "[N/A]", Modifier.padding(8.dp))
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                DateUtils.getRelativeTimeSpanString(
                    notification.timestamp,
                    System.currentTimeMillis(),
                    DateUtils.MINUTE_IN_MILLIS,
                ).toString(),
                Modifier.padding(top = 4.dp, bottom = 8.dp, end = 8.dp, start = 8.dp),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.LightGray,
                ),
            )
        }
    }
}

@Composable
private fun NotificationIcon(notification: Notification) {
    val icon = notification.icons.iconSmall ?: notification.icons.iconLarge
        ?: notification.icons.extraLarge
    if (icon != null) {
        val iconImageBitmap = icon.asImageBitmap(LocalContext.current)
        iconImageBitmap?.let { bitmap ->
            Image(
                bitmap = bitmap,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
        } ?: Icon(
            imageVector = Icons.Outlined.BrokenImage,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
    } else {
        Icon(
            imageVector = Icons.Outlined.BrokenImage,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
    }
}

private fun Icon.asImageBitmap(context: Context): ImageBitmap? {
    return loadDrawable(context)?.toBitmap()?.asImageBitmap()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotificationsListTopAppBar() {
    TopAppBar(
        title = {
            Text(
                stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineMedium,
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
    )
}
