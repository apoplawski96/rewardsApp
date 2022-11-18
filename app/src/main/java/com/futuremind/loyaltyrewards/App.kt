package com.futuremind.loyaltyrewards

import android.app.Application
import com.futuremind.loyaltyrewards.di.applicationModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class App : Application() {

    private val koinModules: Array<Module> = arrayOf(

    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(*koinModules + applicationModule(this@App.applicationContext))
        }
    }
}