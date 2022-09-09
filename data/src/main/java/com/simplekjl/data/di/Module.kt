package com.simplekjl.data.di

import com.simplekjl.data.local.LocalSource
import com.simplekjl.data.local.LocalSourceImpl
import com.simplekjl.data.StoresRepositoryImpl
import com.simplekjl.domain.StoresRepository
import org.koin.dsl.module

val dataModule = module {
    factory<StoresRepository> { StoresRepositoryImpl(get()) }
    factory<LocalSource> { LocalSourceImpl(get()) }
}
