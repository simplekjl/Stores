package com.simplekjl.domain.di

import com.simplekjl.domain.usecase.GetAllRestaurantsUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val domainModule = module {
    factory { GetAllRestaurantsUseCase(Dispatchers.IO, get()) }
}
