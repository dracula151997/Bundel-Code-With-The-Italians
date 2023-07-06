package com.dracula.bundelcodewiththeitalians

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
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
        _notificationFlow.value = activeNotifications.map { it.text }
        Timber.d("onListenerConnected")
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        isConnected = false
        Timber.d("onListenerDisconnected")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        _notificationFlow.value = activeNotifications.map { it.text }
        Timber.d("onNotificationPosted: $sbn")
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        Timber.d("onNotificationRemoved: $sbn")
        _notificationFlow.value = activeNotifications.map { it.text }
    }

    companion object {
        private val _notificationFlow = MutableStateFlow(emptyList<String>())
        val notificationFlow = _notificationFlow.asStateFlow()
    }
}
