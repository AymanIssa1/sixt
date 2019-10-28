package com.example.sixttask

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.sixttask.di.appModule
import com.example.sixttask.di.remoteDataSourceModule
import com.example.sixttask.di.roomDataSourceModule
import com.facebook.stetho.Stetho
import com.zplesac.connectionbuddy.ConnectionBuddy
import com.zplesac.connectionbuddy.ConnectionBuddyConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)

        // start Koin context
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, remoteDataSourceModule(), roomDataSourceModule))
        }

        // To track internet Connectivity
        val networkInspectorConfiguration = ConnectionBuddyConfiguration.Builder(this).build()
        ConnectionBuddy.getInstance().init(networkInspectorConfiguration)

    }

}