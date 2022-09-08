package com.simplekjl.data.di

import com.simplekjl.data.StoresRepositoryImpl
import com.simplekjl.domain.StoresRepository
import org.koin.dsl.module

val dataModule = module {
    factory<StoresRepository> { StoresRepositoryImpl() }
}