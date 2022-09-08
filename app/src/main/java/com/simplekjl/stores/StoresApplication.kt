package com.simplekjl.stores

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.res.AssetManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class StoresApplication : Application() {

    private val androidModule = module {
        single<AssetManager> { get<Context>().assets }
    }

    override fun onCreate() {
        super.onCreate()
        //start koin
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@StoresApplication)
            modules(
                listOf(
                    androidModule
                )
            )
        }
    }
}