package com.simplekjl.stores.di

import android.content.Context
import android.content.res.AssetManager
import org.koin.dsl.module


val androidModule = module {
    single<AssetManager> { get<Context>().assets }
}