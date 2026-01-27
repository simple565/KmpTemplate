package com.kmp.template.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.kmp.template.database.AppDatabase
import java.nio.file.Paths
import kotlin.io.path.pathString


class Factory : IFactory {
    override fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        val dbFilePath = Paths.get(System.getProperty("user.dir"), AppDatabase.DB_FILE_NAME).pathString
        return Room.databaseBuilder<AppDatabase>(name = dbFilePath)
    }
}

actual fun getFactory(): IFactory {
    return Factory()
}