@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.kmp.template.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.kmp.template.DemoApp
import com.kmp.template.database.AppDatabase

class Factory(): IFactory {
    override fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        val context = DemoApp.application.applicationContext
        val dbFile = context.getDatabasePath(AppDatabase.DB_FILE_NAME)
        return Room.databaseBuilder<AppDatabase>(context = context, name = dbFile.absolutePath,)

    }
}

actual fun getFactory(): IFactory {
    return Factory()
}