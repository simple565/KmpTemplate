package com.kmp.template.di

import com.kmp.template.IosDatabaseFactory
import com.kmp.template.database.AppDatabase
import com.kmp.template.database.DatabaseFactory
import org.koin.dsl.module

actual fun platformModule() = module {
    single<AppDatabase> {
        DatabaseFactory.createDatabase(IosDatabaseFactory.getDatabaseBuilder())
    }
}