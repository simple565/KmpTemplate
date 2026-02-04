package com.kmp.template.di

import com.kmp.template.database.AppDatabase
import com.kmp.template.database.RoomDao
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            commonModule() + platformModule()
        )
    }
}

fun commonModule() = module {
    single<RoomDao> {
        get<AppDatabase>().roomDao()
    }
}

expect fun platformModule(): Module