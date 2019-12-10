package com.redbox.mirumon.main

import android.app.Application
import com.redbox.mirumon.main.di.modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(modules)
        }
    }
}