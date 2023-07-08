package com.dracula.bundelcodewiththeitalians.notifications

import android.graphics.drawable.Icon

data class Notification(
    val text: String?,
    val title: String?,
    val subText: String?,
    val infoText: String?,
    val titleBig: String?,
    val timestamp: Long,
    val icons: Icons,
) {
    data class Icons(
        val iconLarge: Icon?,
        val iconSmall: Icon?,
        val extraLarge: Icon?,
    )
}
