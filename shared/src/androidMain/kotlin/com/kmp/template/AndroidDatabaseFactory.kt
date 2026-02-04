package com.kmp.template

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kmp.template.database.AppDatabase

object AndroidDatabaseFactory {

    fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
        val dbFile = context.getDatabasePath(AppDatabase.DB_FILE_NAME)
        return Room.databaseBuilder<AppDatabase>(context = context, name = dbFile.absolutePath)
    }
}