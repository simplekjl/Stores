package com.simplekjl.data

import com.simplekjl.data.model.RestaurantDetailsRaw

interface LocalSource {
    fun getStores(): List<RestaurantDetailsRaw>
}
