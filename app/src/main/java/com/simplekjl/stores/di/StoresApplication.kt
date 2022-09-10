package com.simplekjl.stores.di

import android.app.Application
import com.simplekjl.data.di.dataModule
import com.simplekjl.domain.di.domainModule
import com.simplekjl.stores.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class StoresApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //start koin
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@StoresApplication)
            modules(
                listOf(
                    androidModule,
                    dataModule,
                    domainModule
                )
            )
        }
    }
}