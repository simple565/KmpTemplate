package com.kmp.template.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room")
data class Room(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val calories: String,
    val chartEmoji: String,
    val listColumnCount: Int,
    val supportQuickCreate: Boolean,
    val supportCreateList: Boolean,
    val statViewTypes: String,
    val showCalendarView: Boolean,
    val updateTime: Long
)


