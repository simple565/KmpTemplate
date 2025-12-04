package com.kmp.template.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.template.database.Room
import com.kmp.template.di.DbHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * @author Lian
 * @date 2025/12/4
 */
class RoomViewModel : ViewModel() {

    val roomList = DbHelper.instance.roomDao().getAllAsFlow()
        .stateIn(
            scope = viewModelScope,
            initialValue = emptyList(),
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
}