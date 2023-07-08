package com.dracula.bundelcodewiththeitalians.notifications.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.dracula.bundelcodewiththeitalians.notifications.Notification
import com.dracula.bundelcodewiththeitalians.notifications.util.text
import com.dracula.bundelcodewiththeitalians.notifications.util.toNotification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class BundelNotificationListenerService : NotificationListenerService() {

    var isConnected = false

    override fun getActiveNotifications(): Array<StatusBarNotification> {
        Timber.d("getActiveNotifications")
        return super.getActiveNotifications()
    }


    override fun onListenerConnected() {
        super.onListenerConnected()
        isConnected = true
        _notificationFlow.value = activeNotifications.map { it.toNotification() }.sortedByDescending { it.timestamp }
        Timber.d("onListenerConnected")
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        isConnected = false
        Timber.d("onListenerDisconnected")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        _notificationFlow.value = activeNotifications.map { it.toNotification() }.sortedByDescending { it.timestamp }
        Timber.d("onNotificationPosted: $sbn")
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        Timber.d("onNotificationRemoved: $sbn")
        _notificationFlow.value = activeNotifications.map { it.toNotification() }.sortedByDescending { it.timestamp }
    }

    companion object {
        private val _notificationFlow = MutableStateFlow(emptyList<Notification>())
        val notificationFlow = _notificationFlow.asStateFlow()
    }
}
