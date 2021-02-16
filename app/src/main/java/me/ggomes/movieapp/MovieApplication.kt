package me.ggomes.movieapp

import android.app.Application
import me.ggomes.movieapp.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModules)
            androidContext(this@MovieApplication)
        }
    }
}