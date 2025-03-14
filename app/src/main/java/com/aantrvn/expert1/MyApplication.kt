package com.aantrvn.expert1

import android.app.Application
import android.util.Log
import com.aantrvn.expert1.core.di.databaseModule
import com.aantrvn.expert1.core.di.networkModule
import com.aantrvn.expert1.core.di.repositoryModule
import com.aantrvn.expert1.di.useCaseModule
import com.aantrvn.expert1.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("MyApplication", "Koin Started")
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}