package com.kmp.template

import android.app.Application

class DemoApp : Application() {
    companion object {
        lateinit var application: Application
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}