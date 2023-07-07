package com.dracula.bundelcodewiththeitalians.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

fun Lifecycle.eventAsFlow() = callbackFlow {
    val listener = LifecycleEventObserver { _, event ->
        trySend(event)
    }
    addObserver(listener)
    awaitClose { removeObserver(listener) }
}
