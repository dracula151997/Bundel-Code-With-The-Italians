package com.dracula.bundelcodewiththeitalians.notifications.util

import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.service.notification.StatusBarNotification
import com.dracula.bundelcodewiththeitalians.notifications.Notification
import android.app.Notification as AndroidNotification

val StatusBarNotification.title: String?
    get() = notification.extras.getString(AndroidNotification.EXTRA_TITLE)
val StatusBarNotification.text: String?
    get() = notification.extras.getString(AndroidNotification.EXTRA_TEXT)
val StatusBarNotification.subText: String?
    get() = notification.extras.getString(AndroidNotification.EXTRA_SUB_TEXT)
val StatusBarNotification.infoText: String?
    get() = notification.extras.getString(AndroidNotification.EXTRA_INFO_TEXT)
val StatusBarNotification.titleBig: String?
    get() = notification.extras.getString(AndroidNotification.EXTRA_TITLE_BIG)
val StatusBarNotification.timestamp: Long
    get() = notification.`when`
val StatusBarNotification.smallIcon: Icon?
    get() = notification.smallIcon
val StatusBarNotification.largeIcon: Icon?
    get() = notification.getLargeIcon()
val StatusBarNotification.extraLarge: Icon?
    get() = notification.extras.getParcelable<Bitmap?>(AndroidNotification.EXTRA_LARGE_ICON_BIG)
        ?.let { Icon.createWithBitmap(it) }

fun StatusBarNotification.toNotification(): Notification {
    return Notification(
        title = title,
        text = text,
        subText = subText,
        infoText = infoText,
        titleBig = titleBig,
        timestamp = timestamp,
        icons = Notification.Icons(
            iconLarge = largeIcon,
            iconSmall = smallIcon,
            extraLarge = extraLarge,
        )
    )
}
