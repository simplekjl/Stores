package com.simplekjl.data.di

import com.simplekjl.data.local.LocalSource
import com.simplekjl.data.local.LocalSourceImpl
import com.simplekjl.data.RestaurantsRepositoryImpl
import com.simplekjl.domain.repository.RestaurantsRepository
import org.koin.dsl.module

val dataModule = module {
    factory<RestaurantsRepository> { RestaurantsRepositoryImpl(get()) }
    factory<LocalSource> { LocalSourceImpl(get()) }
}
