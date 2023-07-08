package com.dracula.bundelcodewiththeitalians.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.DefaultLifecycleObserver

@Composable
fun RunOnActivityStart(onStart: () -> Unit) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val lifecycleObserver = remember {
        object : DefaultLifecycleObserver {
            override fun onStart(owner: androidx.lifecycle.LifecycleOwner) {
                super.onStart(owner)
                onStart()
            }
        }
    }
    DisposableEffect(lifecycleObserver, lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }
}
