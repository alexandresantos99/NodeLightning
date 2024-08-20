package com.alexandresantos.nodelightning.features

import android.app.Application
import com.alexandresantos.nodelightning.features.di.appModules
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                appModules
            )
        }
    }
}