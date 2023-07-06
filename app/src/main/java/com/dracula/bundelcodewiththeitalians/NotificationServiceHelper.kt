package com.dracula.bundelcodewiththeitalians

import android.app.Notification
import android.content.ComponentName
import android.content.Context
import android.provider.Settings
import android.service.notification.StatusBarNotification

internal fun needsNotificationsPermission(context: Context): Boolean {
    val pkgName = context.packageName
    val enabledListeners = Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners")
        .split(":")
    if (enabledListeners.isEmpty()) return false

    return enabledListeners
        .map { listenerPackageName -> ComponentName.unflattenFromString(listenerPackageName) }
        .none { pkgName == it?.packageName }
}

val StatusBarNotification.title: String
    get() = notification.extras.getString(Notification.EXTRA_TITLE).orEmpty()
val StatusBarNotification.text: String
    get() = notification.extras.getString(Notification.EXTRA_TEXT).orEmpty()
val StatusBarNotification.subText: String
    get() = notification.extras.getString(Notification.EXTRA_SUB_TEXT).orEmpty()
val StatusBarNotification.infoText: String
    get() = notification.extras.getString(Notification.EXTRA_INFO_TEXT).orEmpty()
val StatusBarNotification.titleBig: String
    get() = notification.extras.getString(Notification.EXTRA_TITLE_BIG).orEmpty()
