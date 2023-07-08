package com.dracula.bundelcodewiththeitalians.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dracula.bundelcodewiththeitalians.notifications.service.BundelNotificationListenerService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class NotificationsListViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(State(emptyList(), false))
    val state: Flow<State> = _state

    init {
        startObserving()
    }

    private fun startObserving() {
        viewModelScope.launch {
            BundelNotificationListenerService.notificationFlow.collect { notifications ->
                _state.value = State(notifications, isConnected = true)
            }
        }
    }

    data class State(
        val notifications: List<Notification>,
        val isConnected: Boolean
    ){
        val hasNotifications: Boolean
            get() = notifications.isNotEmpty()
    }
}
