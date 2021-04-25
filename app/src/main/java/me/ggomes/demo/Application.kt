package me.ggomes.demo

import android.app.Application
import me.ggomes.demo.common.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModules)
            androidContext(this@Application)
        }
    }
}