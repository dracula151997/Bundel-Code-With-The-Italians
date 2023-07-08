package com.dracula.bundelcodewiththeitalians.notifications.util

import android.content.ComponentName
import android.content.Context
import android.provider.Settings

internal fun needsNotificationsPermission(context: Context): Boolean {
    val pkgName = context.packageName
    val enabledListeners =
        Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners")
            .split(":")
    if (enabledListeners.isEmpty()) return false

    return enabledListeners
        .map { listenerPackageName -> ComponentName.unflattenFromString(listenerPackageName) }
        .none { pkgName == it?.packageName }
}
