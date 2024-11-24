package com.example.disampleproject

import android.app.Application
import com.example.disampleproject.di.koin.appModule
import com.example.disampleproject.di.koin.networkModule
import com.example.disampleproject.di.manual.AppContainer
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@HiltAndroidApp
class MyApplication : Application() {

    val appContainer = AppContainer()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule, networkModule)
        }
    }
}