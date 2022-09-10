package com.simplekjl.stores.di

import android.content.Context
import android.content.res.AssetManager
import com.simplekjl.stores.ui.screens.restaurants.RestaurantsViewModel
import com.simplekjl.stores.ui.screens.search.RestaurantSearchViewModel
import org.koin.dsl.module


val androidModule = module {
    single<AssetManager> { get<Context>().assets }
    factory { RestaurantsViewModel(get()) }
    factory { RestaurantSearchViewModel(get()) }
}