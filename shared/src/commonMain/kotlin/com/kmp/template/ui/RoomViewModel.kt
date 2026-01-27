package com.kmp.template.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.template.database.Room
import com.kmp.template.di.DbHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * @author Lian
 * @date 2025/12/4
 */
class RoomViewModel : ViewModel() {

    private val _loadRoomIdState = MutableStateFlow(0)

    val roomList = DbHelper.instance.roomDao().getRoomList()
        .onCompletion { print("onCompletion: load room list") }
        .stateIn(
            scope = viewModelScope,
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed()
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val room = _loadRoomIdState.flatMapLatest { DbHelper.instance.roomDao().getRoom(it) }
        .onCompletion { print("onCompletion: load room") }
        .stateIn(
            scope = viewModelScope,
            initialValue = null,
            started = SharingStarted.WhileSubscribed()
        )

    fun addRoom() = viewModelScope.launch(Dispatchers.IO) {
        val room = Room(
            name = "Room ${Random.nextInt(10)}",
            chartEmoji = "",
            listColumnCount = 3,
            supportCreateList = false,
            supportQuickCreate = false,
            showCalendarView = false,
            updateTime = "",
        )
        DbHelper.instance.roomDao().insert(room)
    }

    fun loadRoom(id: Int) {
        _loadRoomIdState.value = id
    }
}