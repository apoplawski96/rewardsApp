package com.futuremind.loyaltyrewards

import android.app.Application
import com.futuremind.loyaltyrewards.common.util.di.commonModule
import com.futuremind.loyaltyrewards.di.applicationModule
import com.futuremind.loyaltyrewards.di.viewModelsModule
import com.futuremind.loyaltyrewards.feature.dogs.dependency.di.fmRewardsModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class App : Application() {

    private val koinModules: Array<Module> = arrayOf(
        commonModule, viewModelsModule, fmRewardsModule,
    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(*koinModules + applicationModule(this@App.applicationContext))
        }
    }
}