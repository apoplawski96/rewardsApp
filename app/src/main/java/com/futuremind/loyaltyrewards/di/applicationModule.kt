package com.futuremind.loyaltyrewards.di

import android.content.Context
import org.koin.dsl.module

internal fun applicationModule(context: Context) = module {
    single<Context> { context }
}