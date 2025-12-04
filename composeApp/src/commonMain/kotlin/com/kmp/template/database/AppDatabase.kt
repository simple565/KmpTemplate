package com.kmp.template.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.kmp.template.di.getFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlin.jvm.JvmStatic

@Database(entities = [Room::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_FILE_NAME = "room.db"

        @JvmStatic
        val instance by lazy {
            getFactory().getDatabaseBuilder()
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
    }

    abstract fun roomDao(): RoomDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
