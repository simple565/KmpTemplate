package com.kmp.template.di

import com.kmp.template.JvmDatabaseFactory
import com.kmp.template.database.AppDatabase
import com.kmp.template.database.DatabaseFactory
import org.koin.dsl.module

actual fun platformModule() = module {
    single<AppDatabase> {
        DatabaseFactory.createDatabase(JvmDatabaseFactory.getDatabaseBuilder())
    }
}