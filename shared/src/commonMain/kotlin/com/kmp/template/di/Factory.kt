package com.kmp.template.di

import androidx.room.RoomDatabase
import com.kmp.template.database.AppDatabase


interface IFactory {
    fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
}

expect fun getFactory(): IFactory
