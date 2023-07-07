package com.dracula.bundelcodewiththeitalians.onboarding

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.dracula.bundelcodewiththeitalians.notifications.util.needsNotificationsPermission
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
internal class OnBoardingViewModel @Inject constructor(
    private val application: Application
) : ViewModel() {

    private val _state = MutableStateFlow(State(false))
    val state: Flow<State> = _state

    fun checkIfNeedsNotificationsPermission() {
        _state.value = State(needsNotificationsPermission(application))
    }

    data class State(val needsPermission: Boolean)
}
