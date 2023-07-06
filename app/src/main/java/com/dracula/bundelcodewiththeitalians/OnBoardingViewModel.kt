package com.dracula.bundelcodewiththeitalians

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class OnBoardingViewModel : ViewModel() {

    private val _state = MutableStateFlow(State(false))
    val state: Flow<State> = _state

    fun checkIfNeedsNotificationsPermission(context: Context) {
        _state.value = State(needsNotificationsPermission(context))
    }

    data class State(val needsPermission: Boolean)
}
